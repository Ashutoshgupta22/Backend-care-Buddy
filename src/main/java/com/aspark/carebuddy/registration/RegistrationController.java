package com.aspark.carebuddy.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
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
    public RegistrationRequest registerNurse(@RequestBody RegistrationRequest request) {

        return registrationService.signupNurse(request);
    }

    @GetMapping(path = "confirm-user")
    public String confirmUser(@RequestParam("token") String token) {
        return registrationService.confirmUserToken(token);
    }


}
