package org.mitraz.MITRAz;

import org.junit.jupiter.api.Test;
import org.mitraz.MITRAz.model.nurse.Nurse;
import org.mitraz.MITRAz.model.nurse.NurseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MitrazApplicationTests {
	
	@Autowired
	private NurseDao nurseDao;

	@Test
	void addNurse() {
		
		Nurse nurse = new Nurse();
		nurse.setName("Billy butcher");
		nurse.setAge(55);
		nurse.setLocation("21st street,brooklyn,New york city, 230012");
		
		nurseDao.saveNurse(nurse);
		
	}

}
