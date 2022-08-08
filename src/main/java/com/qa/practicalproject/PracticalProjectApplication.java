package com.qa.practicalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.qa.persistence")
@SpringBootApplication
public class PracticalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticalProjectApplication.class, args);
		System.out.println("SpringBoot project running OK");
	}

}
