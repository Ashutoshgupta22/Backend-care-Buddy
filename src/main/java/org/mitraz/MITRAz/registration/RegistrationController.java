package org.mitraz.MITRAz.registration;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.user.User;
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
