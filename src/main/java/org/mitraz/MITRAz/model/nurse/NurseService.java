package org.mitraz.MITRAz.model.nurse;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.LocationData;
import org.mitraz.MITRAz.exception.EmailExistsException;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenNurse;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenUser;
import org.mitraz.MITRAz.registration.token.ConfirmationTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@Service
public class NurseService {
	
	private NurseRepository nurseRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ConfirmationTokenService confirmationTokenService;

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
