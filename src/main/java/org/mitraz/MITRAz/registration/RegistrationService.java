package org.mitraz.MITRAz.registration;


import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.model.user.User;
import org.mitraz.MITRAz.security.UserRole;
import org.mitraz.MITRAz.security.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public User register(RegistrationRequest request) {

        boolean isValidEmail =  emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }

        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLocation(request.getLocation());
        user.setUserRole(UserRole.USER);
        return userService.signUpUser(user);
    }
}
