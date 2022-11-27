package org.mitraz.MITRAz.model.nurse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NurseDao {
	
	@Autowired
	private NurseRepository repository;
	
	
	public Nurse saveNurse(Nurse nurse) {
		return repository.save(nurse);
	}
	
	public void deleteNurse(Nurse nurse) {
		repository.delete(nurse);
	}

	public List<Nurse> getAllNurse() {

		//Convert iterable type to list
		List<Nurse> nurseList = new ArrayList<>();
		Streamable.of(repository.findAll())
				.forEach(nurseList::add);
		return nurseList;
	}

	public void deleteNurseById(int id) {

		repository.deleteById(id);
	}

	public boolean findById(int id) {
		return repository.findById(id).isPresent();
	}


}
