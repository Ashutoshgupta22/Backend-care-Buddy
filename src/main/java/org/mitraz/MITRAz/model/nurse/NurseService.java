package org.mitraz.MITRAz.model.nurse;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.request.LocationData;
import org.mitraz.MITRAz.api.request.LoginRequest;
import org.mitraz.MITRAz.exception.EmailExistsException;
import org.mitraz.MITRAz.exception.EmailNotFoundException;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenNurse;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mitraz.MITRAz.model.user.UserService.USER_NOT_FOUND_MSG;

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
		nurseRepository.save(nurse);

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


}
