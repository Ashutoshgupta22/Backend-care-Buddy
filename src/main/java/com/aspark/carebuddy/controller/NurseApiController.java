package com.aspark.carebuddy.controller;

import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.api.request.LoginRequest;
import com.aspark.carebuddy.api.response.NurseResponse;
import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.nurse.NurseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RequestMapping("api/nurse")
@RestController
public class NurseApiController {

    NurseService nurseService;

    @GetMapping("{id}")
    public ResponseEntity<NurseResponse> getNurseById(@PathVariable int id) {

        return nurseService.getNurseById(id);
    }

    @PostMapping("/set-firebase-token/{email}")
    public Boolean setNurseFirebaseToken(@PathVariable String email,
                                        @RequestBody String firebaseToken) {

        // token received is in double quotes, remove them
        if (firebaseToken.startsWith("\"") && firebaseToken.endsWith("\"")){
            firebaseToken = firebaseToken.replaceAll("\"","");
        }

        return nurseService.setFirebaseToken(email, firebaseToken);

    }

    @PostMapping("login")
    ResponseEntity<NurseResponse> loginNurse(@RequestBody LoginRequest loginRequest) {

        System.out.println("Received login request: "+loginRequest.toString());
        return nurseService.loginNurse(loginRequest);
    }

    @PostMapping("save-location")
    boolean nurseSaveLocation(@RequestBody LocationData locationData) {

        return nurseService.nurseSaveLocation(locationData);
    }

    @GetMapping(path = "confirm")
    public String confirmNurse(@RequestParam("token") String token){

        return nurseService.confirmNurseToken(token);
    }

    @PostMapping("upload-profile-pic")
    public ResponseEntity<String> uploadProfilePic(
            @RequestPart("image")MultipartFile imageFile,
            @RequestParam("nurseId") int nurseId) {

        return nurseService.uploadProfilePic(imageFile, nurseId);
    }


}
