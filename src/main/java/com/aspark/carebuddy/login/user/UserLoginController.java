package com.aspark.carebuddy.login.user;

import com.aspark.carebuddy.api.request.LoginRequest;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.User;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {

      return   userLoginService.loginUser(loginRequest);

    }
}
