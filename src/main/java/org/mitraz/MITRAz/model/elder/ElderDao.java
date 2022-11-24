package org.mitraz.MITRAz.model.elder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElderDao {
	
	@Autowired
	private ElderRepository repository;
	
	public void saveElder(Elder elder) {
		
		repository.save(elder);
	}
	
	public void deleteElder(Elder elder) {
		
		repository.delete(elder);
	}

}
