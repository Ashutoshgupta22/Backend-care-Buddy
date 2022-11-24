package org.mitraz.MITRAz;

import org.junit.jupiter.api.Test;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.nurse.NurseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MitrAzApplicationTests {
	
	@Autowired
	private NurseDao nurseDao;

	@Test
	void addNurse() {
		
		Nurse nurse = new Nurse();
		nurse.setName("Vijay Singh");
		nurse.setAge(23);
		nurse.setLocation("ward 25, Whitefield,Bengaluru, 560037");
		
		nurseDao.saveNurse(nurse);
		
	}

}
