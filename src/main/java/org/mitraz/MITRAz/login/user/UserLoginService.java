package org.mitraz.MITRAz.login.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.request.LoginRequest;
import org.mitraz.MITRAz.model.user.User;
import org.mitraz.MITRAz.model.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UserLoginService {

    UserService userService;

    public Map<String, String> loginUser(LoginRequest loginRequest) {

        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());

      return userService.loginUser(user);

    }
}
