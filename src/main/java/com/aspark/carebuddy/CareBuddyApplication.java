package com.aspark.carebuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.servlet.annotation.MultipartConfig;

@EnableCaching
@MultipartConfig
@SpringBootApplication( scanBasePackages="com.aspark.carebuddy")
public class CareBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareBuddyApplication.class, args);
	}

}
