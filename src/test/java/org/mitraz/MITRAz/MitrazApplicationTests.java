package org.mitraz.MITRAz;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mitraz.MITRAz.model.nurse.NurseService;
import org.mitraz.MITRAz.model.nurse.NurseRepository;
import org.springframework.boot.test.context.SpringBootTest;

@AllArgsConstructor
@SpringBootTest
class MitrazApplicationTests {

	private NurseService nurseService;
	private NurseRepository nurseRepository;

//	@Test
//	void addNurse() {
//
//		Nurse nurse = new Nurse();
//		nurse.setName("Billy butcher");
//		nurse.setAge(55);
//		//nurse.setLocation("21st street,brooklyn,New york city, 230012");
//
//		nurseDao.saveNurse(nurse);
//
//	}

	@Test
	void getAllNurseLatitude() {

		//System.out.println(nurseRepository.getLatitude().toString());
	}

}
