package com.aspark.carebuddy.model.nurse;

import com.aspark.carebuddy.api.request.LocationData;
import com.aspark.carebuddy.api.request.LoginRequest;
import com.aspark.carebuddy.exception.EmailNotFoundException;
import com.aspark.carebuddy.registration.token.ConfirmationTokenNurse;
import com.aspark.carebuddy.registration.token.ConfirmationTokenService;
import com.aspark.carebuddy.repository.NurseRepository;
import lombok.AllArgsConstructor;
import com.aspark.carebuddy.exception.EmailExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static com.aspark.carebuddy.model.user.UserService.USER_NOT_FOUND_MSG;

@AllArgsConstructor
@Service
public class NurseService {
	
	private NurseRepository nurseRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ConfirmationTokenService confirmationTokenService;


	public Nurse loadNurseByEmail(String email) throws EmailNotFoundException {

		return nurseRepository.findByEmail(email)
				.orElseThrow(() -> new EmailNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
	}


	public ResponseEntity<Nurse> loginNurse(LoginRequest loginRequest) {

		boolean isNursePresent =  nurseRepository.findByEmail(loginRequest.getEmail()).isPresent();

		if (isNursePresent) {

			Nurse dbNurse =  loadNurseByEmail(loginRequest.getEmail());
			String dbPassword = dbNurse.getPassword();

			if (bCryptPasswordEncoder.matches(loginRequest.getPassword(),dbPassword))
				return ResponseEntity.ok().body(dbNurse);

			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

	public boolean nurseSaveLocation(LocationData locationData) {

		int result = nurseRepository.nurseSaveLocation(locationData.getLatitude(),
				locationData.getLongitude(),
				locationData.getPincode(),
				locationData.getUsername());

		System.out.println("nurseSaveLocation: rows updated- "+result);

		return result==1;
	}

public ArrayList<Nurse> getNurseAtPincode(String pincode){

		return nurseRepository.getNurseAtPincode(pincode);
	}

	public String signUpNurse(Nurse nurse) {

		boolean nurseExists = nurseRepository.findByEmail(nurse.getEmail()).isPresent();

		if (nurseExists)
			throw new EmailExistsException();

		String encodedPassword = bCryptPasswordEncoder.encode(nurse.getPassword());
		nurse.setPassword(encodedPassword);
		Nurse savedNurse = nurseRepository.save(nurse);

		String token = UUID.randomUUID().toString();
		ConfirmationTokenNurse confirmationTokenNurse = new ConfirmationTokenNurse(token,
				LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
				nurse);

		confirmationTokenService.saveConfirmationNurseToken(confirmationTokenNurse);

		//TODO :Send email
		return token;
	}

	public int enableNurse(String email) {

		return nurseRepository.enableNurse(email);
	}


    public Boolean setFirebaseToken(String email, String firebaseToken) {

		int success = nurseRepository.setFirebaseToken(email, firebaseToken);
		return success == 1;
    }

	public ArrayList<Nurse> getTopNurses(String pincode) {

		return nurseRepository.getTopNurses(pincode);
	}

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
