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


    public Boolean setFirebaseToken(String email, String firebaseToken) {

		int success = nurseRepository.setFirebaseToken(email, firebaseToken);
		return success == 1;
    }

	public ArrayList<Nurse> getTopNurses(String pincode) {

		return nurseRepository.getTopNurses(pincode);
	}
}
