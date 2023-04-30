package org.mitraz.MITRAz.model.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.LocationData;
import org.mitraz.MITRAz.exception.EmailExistsException;
import org.mitraz.MITRAz.exception.UserEmailNotFoundException;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.nurse.NurseService;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenUser;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final NurseService nurseService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserEmailNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserEmailNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(User user) {

        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {

           // throw new IllegalStateException("Email already registered");
            throw new EmailExistsException();
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

      String  token = UUID.randomUUID().toString();
        ConfirmationTokenUser confirmationTokenUser = new ConfirmationTokenUser(token,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationUserToken(confirmationTokenUser);

        //TODO :Send email
        return token;

    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }


    public Map<String, String> loginUser(User user) {

        Map<String,String> response;
        System.out.println(user.getEmail() +" "+user.getPassword());
        String message,status;

      boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

      if (userExists) {

          String databasePassword = loadUserByUsername(user.getEmail()).getPassword();

          if (bCryptPasswordEncoder.matches(user.getPassword(),databasePassword)) {
              message = "Authentication successful";
              status=HttpStatus.OK.toString();
          }
          else {
                    message= "Username or Password did not match.";
                    status=HttpStatus.UNAUTHORIZED.toString();
          }
      }
      else  {
          message= "User does not exists";
          status = HttpStatus.UNAUTHORIZED.toString();
      }

       response  = Map.of(
                "message",message,
                "status", status
        );

        return response;
    }

    public boolean saveLocation(LocationData locationData) {

        double latitude = locationData.getLatitude();
        double longitude = locationData.getLongitude();
        String username = locationData.getUsername();
        String pincode = locationData.getPincode();

       int updatedRow = userRepository.saveLocation(latitude,longitude,pincode,username);

       System.out.println("saveLocation returned: "+updatedRow);

        return updatedRow == 1;
    }

    //Method for end point
    public Map<String, Object> getUserData(String email) {

        System.out.println("Getting user data");

        User user = (User) loadUserByUsername(email);

        Map<String,Object> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("email", user.getEmail());
        userMap.put("latitude",user.getLatitude());
        userMap.put("longitude",user.getLongitude());
        userMap.put("pincode",user.getPincode());

        return userMap;
    }

    public Nurse bookService(String email) {

        Map<String,Object> user = getUserData(email);
        String pincode = user.get("pincode").toString();

        System.out.println("pincode="+pincode);

        ArrayList<Nurse> nurseList = nurseService.getNurseAtPincode(pincode);

        //TODO if nurseList is empty or null
        assert nurseList !=null;
        assert !nurseList.isEmpty();
        System.out.println("nurseList size="+nurseList.size());
        System.out.println("nurseList= "+ nurseList.get(0).toString());

        return nurseList.get(0);

    }
}
