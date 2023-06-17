package com.aspark.carebuddy.api;

import com.aspark.carebuddy.api.request.BookServiceRequest;
import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.model.nurse.Nurse;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserApiController {

    UserService userService;

    @PostMapping("/set-firebase-token/{email}")
    public Boolean setUserFirebaseToken(@PathVariable String email,
                                        @RequestBody String firebaseToken) {

        return userService.setFirebaseToken(email, firebaseToken);

    }

    @PostMapping("save-location")
    public boolean saveUserLocation(@RequestBody LocationData locationData) {

        return userService.saveLocation(locationData);
    }

    @GetMapping("get-user/{email}")
    public Map<String,Object> getUserData( @PathVariable String email) {

        return userService.getUserData(email);
    }

    @PostMapping("book-service")
    public Nurse bookService(@RequestBody BookServiceRequest request){

        System.out.println("book service api call received for "+request.getEmail());
        return userService.bookService(request.getEmail());
    }

}
