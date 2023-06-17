package com.aspark.carebuddy.login.user;

import com.aspark.carebuddy.api.request.LoginRequest;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.User;
import com.aspark.carebuddy.model.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginService {

    UserService userService;

    public ResponseEntity<User> loginUser(LoginRequest loginRequest) {

        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());

      return userService.loginUser(user);

    }
}
