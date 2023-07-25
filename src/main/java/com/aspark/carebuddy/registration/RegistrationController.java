package com.aspark.carebuddy.registration;

import com.aspark.carebuddy.model.nurse.Nurse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("user/signup")
    public Boolean registerUser(@RequestBody RegistrationRequest request) {

        return registrationService.registerUser(request);
    }

    @PostMapping("nurse/signup")
    public Nurse registerNurse(@RequestBody RegistrationRequest request) {

        return registrationService.signupNurse(request);
    }

    @GetMapping(path = "confirm-user")
    public String confirmUser(@RequestParam("token") String token) {
        return registrationService.confirmUserToken(token);
    }

    @GetMapping(path = "confirm-nurse")
    public String confirmNurse(@RequestParam("token") String token){

        return registrationService.confirmNurseToken(token);
    }
}
