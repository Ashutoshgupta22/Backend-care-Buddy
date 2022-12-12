package org.mitraz.MITRAz.login.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.user.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/login")
public class UserLoginController {

    private UserLoginService userLoginService;

    @PostMapping
    public String loginUser(@RequestBody UserLoginRequest userLoginRequest) {

      return   userLoginService.loginUser(userLoginRequest);

    }
}
