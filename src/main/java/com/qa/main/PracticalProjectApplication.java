package com.qa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan("com.qa.persistence")
@SpringBootApplication
public class PracticalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticalProjectApplication.class, args);
		
		System.out.println("spring app running ok");
	}

	
	
}
