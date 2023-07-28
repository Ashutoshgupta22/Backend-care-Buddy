package com.aspark.carebuddy.registration;


import com.aspark.carebuddy.api.response.NurseResponse;
import com.aspark.carebuddy.email.EmailSender;
import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.nurse.NurseService;
import com.aspark.carebuddy.model.user.User;
import com.aspark.carebuddy.registration.token.ConfirmationTokenNurse;
import com.aspark.carebuddy.registration.token.ConfirmationTokenService;
import com.aspark.carebuddy.registration.token.ConfirmationTokenUser;
import com.aspark.carebuddy.security.UserRole;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.model.user.UserService;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final NurseService nurseService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public Boolean registerUser(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirebaseToken(request.getFirebaseToken());
        user.setUserRole(UserRole.USER);

        String token = userService.signUpUser(user);

        String link = "http://localhost:8080/api/registration/confirm?token=" + token;
        emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link));

        return true;
    }

    public RegistrationRequest signupNurse(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }

        Gson gson = new Gson();
        String specialitiesJson = gson.toJson(request.getSpecialities());

        Nurse nurse = new Nurse();
        nurse.setFirstName(request.getFirstName());
        nurse.setLastName(request.getLastName());
        nurse.setAge(request.getAge());
        nurse.setBiography(request.getBiography());
        nurse.setQualifications(request.getQualifications());
        nurse.setExperience(request.getExperience());
        nurse.setSpecialities(specialitiesJson);
        nurse.setEmail(request.getEmail());
        nurse.setPassword(request.getPassword());
        nurse.setPincode(request.getPincode());
        nurse.setLatitude(request.getLatitude());
        nurse.setLongitude(request.getLongitude());
        nurse.setUserRole(UserRole.NURSE);

        Nurse savedNurse = nurseService.signUpNurse(nurse);

//        String link = "http://localhost:8080/api/registration/confirm?token=" + token;
//        emailSender.sendEmail(request.getEmail(), buildEmail(request.getFirstName(), link));

//        Type typeList = new TypeToken<ArrayList<String>>() {}.getType();
//        ArrayList<String> specialitiesList = gson.fromJson(nurse.getSpecialities(), typeList);

        return request;
    }

    public String confirmUserToken(String token) {

        ConfirmationTokenUser confirmationTokenUser = confirmationTokenService.getUserToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));

        if (confirmationTokenUser.getConfirmedAt() != null)
            throw new IllegalStateException("Email already verified");

        LocalDateTime expiresAt = confirmationTokenUser.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Token expired");

        confirmationTokenService.setUserConfirmedAt(token);

        userService.enableUser(confirmationTokenUser.getUser().getEmail());

        return "Email Verified";
    }


//    public String confirmNurseToken(String token) {
//
//        ConfirmationTokenNurse confirmationTokenNurse = confirmationTokenService.getNurseToken(token)
//                .orElseThrow(()-> new IllegalStateException("Token not found"));
//
//        if(confirmationTokenNurse.getConfirmedAt() !=null)
//            throw new IllegalStateException("Email already verified");
//
//        LocalDateTime expiresAt = confirmationTokenNurse.getExpiresAt();
//
//        if(expiresAt.isBefore(LocalDateTime.now()))
//            throw new IllegalStateException("Token expired");
//
//        confirmationTokenService.setNurseConfirmedAt(token);
//
//        nurseService.enableNurse(confirmationTokenNurse.getNurse().getEmail());
//
//        return "Email Verified";
//    }
//
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
