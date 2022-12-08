package org.mitraz.MITRAz.registration;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping(path = "/api/user/registration")
    public User register(@RequestBody RegistrationRequest request) {

        return registrationService.register(request);
    }
}
