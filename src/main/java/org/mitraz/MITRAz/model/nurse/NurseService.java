package org.mitraz.MITRAz.model.nurse;

import lombok.AllArgsConstructor;
import org.mitraz.MITRAz.api.LocationData;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NurseService {
	
	private NurseRepository nurseRepository;

	public boolean nurseSaveLocation(LocationData locationData) {

		int result = nurseRepository.nurseSaveLocation(locationData.getLatitude(),
				locationData.getLongitude(),
				locationData.getUsername());

		System.out.println("nurseSaveLocation: rows updated- "+result);

		return result==1;
	}
}
