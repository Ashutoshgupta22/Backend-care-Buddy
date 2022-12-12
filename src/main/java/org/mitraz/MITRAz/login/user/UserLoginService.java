package org.mitraz.MITRAz.login.user;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.user.User;
import org.mitraz.MITRAz.security.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginService {

    UserService userService;

    public String loginUser(UserLoginRequest userLoginRequest) {

        User user = new User();
        user.setEmail(userLoginRequest.getEmail());
        user.setPassword(userLoginRequest.getPassword());

      return userService.loginUser(user);

    }
}
