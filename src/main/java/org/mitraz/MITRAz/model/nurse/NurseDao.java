package org.mitraz.MITRAz.model.nurse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseDao {
	
	@Autowired
	private NurseRepository repository;
	
	
	public void saveNurse(Nurse nurse) {
		
		repository.save(nurse);
	}
	
	public void deleteNurse(Nurse nurse) {
		
		repository.delete(nurse);
	}

}
