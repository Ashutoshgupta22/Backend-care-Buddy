package com.aspark.carebuddy.registration;

import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("user/registration")
    public User registerUser(@RequestBody RegistrationRequest request) {

        return registrationService.registerUser(request);
    }

    @PostMapping("nurse/registration")
    public Nurse registerNurse(@RequestBody RegistrationRequest request) {

        return registrationService.registerNurse(request);
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
