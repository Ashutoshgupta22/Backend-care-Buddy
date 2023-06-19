package com.aspark.carebuddy.api;

import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.api.request.LoginRequest;
import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.nurse.NurseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("api/nurse")
@RestController
public class NurseApiController {

    NurseService nurseService;

    @PostMapping("/set-firebase-token/{email}")
    public Boolean setNurseFirebaseToken(@PathVariable String email,
                                        @RequestBody String firebaseToken) {

        if (firebaseToken.startsWith("\"") && firebaseToken.endsWith("\"")){
            firebaseToken = firebaseToken.replaceAll("\"","");
        }

        return nurseService.setFirebaseToken(email, firebaseToken);

    }

    @PostMapping("login")
    ResponseEntity<Nurse> loginNurse(@RequestBody LoginRequest loginRequest) {

        System.out.println("Received login request: "+loginRequest.toString());
        return nurseService.loginNurse(loginRequest);
    }

    @PostMapping("save-location")
    boolean nurseSaveLocation(@RequestBody LocationData locationData) {

        return nurseService.nurseSaveLocation(locationData);
    }


}
