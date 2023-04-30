package org.mitraz.MITRAz.login.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.user.User;
import org.mitraz.MITRAz.model.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class UserLoginService {

    UserService userService;

    public Map<String, String> loginUser(UserLoginRequest userLoginRequest) {

        User user = new User();
        user.setEmail(userLoginRequest.getEmail());
        user.setPassword(userLoginRequest.getPassword());

      return userService.loginUser(user);

    }
}
