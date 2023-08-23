package com.aspark.carebuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@SpringBootApplication( scanBasePackages="com.aspark.carebuddy")
//@EnableAutoConfiguration
public class CareBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareBuddyApplication.class, args);
	}

}
