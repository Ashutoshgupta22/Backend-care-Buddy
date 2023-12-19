package com.aspark.carebuddy.controller;

import com.aspark.carebuddy.api.request.LoginRequest;
import com.aspark.carebuddy.login.user.UserLoginService;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@AllArgsConstructor
@RequestMapping("/api/user/login")
public class UserLoginController {

    private UserLoginService userLoginService;

    @PostMapping
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {

      return userLoginService.loginUser(loginRequest);

    }
}
