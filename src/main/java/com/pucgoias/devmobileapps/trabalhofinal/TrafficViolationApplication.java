package com.pucgoias.devmobileapps.trabalhofinal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.TrafficViolationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TrafficViolationApplication {
	@Autowired
	private TrafficViolationRepository trafficViolationRepository;

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(TrafficViolationApplication.class, args);
	}
	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

}
