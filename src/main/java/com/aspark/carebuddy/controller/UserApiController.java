package com.aspark.carebuddy.controller;

import com.aspark.carebuddy.api.request.BookServiceRequest;
import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.api.response.NurseResponse;
import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.nurse.NurseService;
import com.aspark.carebuddy.model.user.User;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserApiController {

    UserService userService;
    NurseService nurseService;

    @PostMapping("/set-firebase-token/{email}")
    public Boolean setUserFirebaseToken(@PathVariable String email,
                                        @RequestBody String firebaseToken) {

        if (firebaseToken.startsWith("\"") && firebaseToken.endsWith("\"")){
           firebaseToken = firebaseToken.replaceAll("\"","");
        }
        return userService.setFirebaseToken(email, firebaseToken);

    }

    @PostMapping("save-location")
    public boolean saveUserLocation(@RequestBody LocationData locationData) {

        return userService.saveLocation(locationData);
    }

    @GetMapping("get-user/{email}")
    public ResponseEntity<User> getUserData(@PathVariable String email) {

        return userService.getUserData(email);
    }

    @PostMapping("book-appointment")
    public Nurse bookAppointment(@RequestBody BookServiceRequest request){

        System.out.println("book appointment api call received for "+request.getUserEmail());
        return userService.bookAppointment(request.getUserEmail(), request.getNurseId());
    }

    @GetMapping("get-top-nurses/{pincode}")
    public ArrayList<NurseResponse> getTopNurses(@PathVariable String pincode) {

        return nurseService.getTopNurses(pincode);
    }

}
