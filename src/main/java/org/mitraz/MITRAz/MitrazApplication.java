package org.mitraz.MITRAz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages="org.mitraz.MITRAz")
//@EnableAutoConfiguration
public class MitrazApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitrazApplication.class, args);
	}

}
