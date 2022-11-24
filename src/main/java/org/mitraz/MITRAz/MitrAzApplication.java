package org.mitraz.MITRAz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages="org.mitraz.MITRAz")
//@EnableAutoConfiguration
public class MitrAzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitrAzApplication.class, args);
	}

}
