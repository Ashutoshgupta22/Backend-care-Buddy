package org.mitraz.MITRAz.registration;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.user.User;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping()
    public User register(@RequestBody RegistrationRequest request) {

        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
