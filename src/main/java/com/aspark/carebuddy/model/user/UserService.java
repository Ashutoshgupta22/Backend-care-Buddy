package com.aspark.carebuddy.model.user;

import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.exception.EmailExistsException;
import com.aspark.carebuddy.exception.EmailNotFoundException;
import com.aspark.carebuddy.firebase.FirebaseCloudMessaging;
import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.nurse.NurseService;
import com.aspark.carebuddy.registration.token.ConfirmationTokenService;
import com.aspark.carebuddy.registration.token.ConfirmationTokenUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    public static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final NurseService nurseService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final FirebaseCloudMessaging firebaseCloudMessaging;

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
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


    public ResponseEntity<User> loginUser(User user) {

        System.out.println(user.getEmail() +" "+user.getPassword());

        boolean isUserPresent = userRepository.findByEmail(user.getEmail()).isPresent();

        if (isUserPresent) {

            User dbUser = (User) loadUserByUsername(user.getEmail());
            String dbPassword = dbUser.getPassword();

            if (bCryptPasswordEncoder.matches(user.getPassword(), dbPassword))
                return new ResponseEntity<>(dbUser,HttpStatus.OK);

            else
                return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);

        } else
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

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

        Nurse selectedNurse = nurseList.get(0);
        String firebaseToken = selectedNurse.getFirebaseToken();

        firebaseCloudMessaging.sendNotification(firebaseToken);

        return selectedNurse;

    }

    public Boolean setFirebaseToken(String email, String firebaseToken) {

        return userRepository.setFirebaseToken(firebaseToken, email);
    }
}
