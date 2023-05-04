package org.mitraz.MITRAz.login.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/login")
public class UserLoginController {

    private UserLoginService userLoginService;

    @PostMapping
    public Map<String, String> loginUser(@RequestBody LoginRequest loginRequest) {

      return   userLoginService.loginUser(loginRequest);

    }
}
