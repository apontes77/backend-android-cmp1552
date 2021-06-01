package com.pucgoias.devmobileapps.trabalhofinal;

import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TrabalhofinalApplication{
	@Autowired
	private DenunciaRepository denunciaRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrabalhofinalApplication.class, args);
	}

}
