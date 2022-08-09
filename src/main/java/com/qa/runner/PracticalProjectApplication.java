package com.qa.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.qa.repositories")
@EntityScan("com.qa.persistence")
@ComponentScan("com.qa.services")
@ComponentScan("com.qa.controllers")
@ComponentScan("com.qa.exceptions")
@SpringBootApplication
public class PracticalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticalProjectApplication.class, args);
		
		System.out.println("spring app running ok");
	}
	
}
