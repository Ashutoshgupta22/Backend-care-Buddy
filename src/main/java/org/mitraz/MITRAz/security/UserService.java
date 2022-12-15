package org.mitraz.MITRAz.security;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.exception.EmailExistsException;
import org.mitraz.MITRAz.model.user.User;
import org.mitraz.MITRAz.model.user.UserDao;
import org.mitraz.MITRAz.model.user.UserRepository;
import org.mitraz.MITRAz.registration.token.ConfirmationToken;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(User user) {

        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {

           // throw new IllegalStateException("Email already registered");
            throw new EmailExistsException();
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

      String  token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token,
                LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO :Send email
        return token;

    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }


    public Map<String, String> loginUser(User user) {

        Map<String,String> response;
        System.out.println(user.getEmail() +" "+user.getPassword());
        String message,status;

      boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

      if (userExists) {

          String databasePassword = loadUserByUsername(user.getEmail()).getPassword();

          if (bCryptPasswordEncoder.matches(user.getPassword(),databasePassword)) {
              message = "Authentication successful";
              status=HttpStatus.OK.toString();
          }
          else {
                    message= "Username or Password did not match.";
                    status=HttpStatus.UNAUTHORIZED.toString();
          }
      }
      else  {
          message= "User does not exists";
          status = HttpStatus.UNAUTHORIZED.toString();
      }

       response  = Map.of(
                "message",message,
                "status", status
        );

        return response;
    }
}
