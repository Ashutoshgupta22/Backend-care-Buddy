package com.aspark.carebuddy.model.user;

import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.api.response.NurseResponse;
import com.aspark.carebuddy.exception.EmailExistsException;
import com.aspark.carebuddy.exception.EmailNotFoundException;
import com.aspark.carebuddy.firebase.FirebaseCloudMessaging;
import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.nurse.NurseService;
import com.aspark.carebuddy.registration.token.ConfirmationTokenService;
import com.aspark.carebuddy.registration.token.ConfirmationTokenUser;
import com.aspark.carebuddy.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final FirebaseCloudMessaging firebaseCloudMessaging ;

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
    public ResponseEntity<User> getUserData(String email) {

        System.out.println("Getting user data");

        User user = (User) loadUserByUsername(email);

        return ResponseEntity.ok(user);
    }

    public Nurse bookService(String email) {

        User user = (User) loadUserByUsername(email);
        String pincode = user.getPincode();

        System.out.println("pincode="+pincode);

      //  ArrayList<Nurse> nurseList = nurseService.getNurseAtPincode(pincode);

        //TODO if nurseList is empty or null
//        assert nurseList !=null;
//        assert !nurseList.isEmpty();
//        System.out.println("nurseList size="+nurseList.size());
//        System.out.println("nurseList= "+ nurseList.get(0).toString());
//
//        Nurse selectedNurse = nurseList.get(0);
//        String firebaseToken = selectedNurse.getFirebaseToken();
//
//        firebaseCloudMessaging.sendNotification(firebaseToken);

        //return selectedNurse;
        return  null;
    }

    public Boolean setFirebaseToken(String email, String firebaseToken) {

        int success = userRepository.setFirebaseToken(firebaseToken, email);
        return success == 1;
    }

//    public ArrayList<NurseResponse> getTopNurses(String pincode) {
//
//        System.out.println("Getting top nurses");
//        return  nurseService.getTopNurses(pincode);
//    }
}
