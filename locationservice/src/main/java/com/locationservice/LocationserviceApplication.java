package com.locationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.locationservice.controller",
											"com.locationservice.service"})
@EntityScan(basePackages = {"com.locationservice.entity"})
@EnableJpaRepositories(basePackages = {"com.locationservice.repository"})

public class LocationserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LocationserviceApplication.class, args);
		
	}

}
