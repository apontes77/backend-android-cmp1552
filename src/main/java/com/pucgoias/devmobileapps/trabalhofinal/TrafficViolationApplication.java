package com.pucgoias.devmobileapps.trabalhofinal;

import com.pucgoias.devmobileapps.trabalhofinal.repositories.TrafficViolationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrafficViolationApplication {
	@Autowired
	private TrafficViolationRepository trafficViolationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrafficViolationApplication.class, args);
	}

}
