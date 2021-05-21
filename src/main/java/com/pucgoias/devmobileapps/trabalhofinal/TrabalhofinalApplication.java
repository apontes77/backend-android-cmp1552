package com.pucgoias.devmobileapps.trabalhofinal;

import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.DenunciaRepository;
import com.pucgoias.devmobileapps.trabalhofinal.services.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TrabalhofinalApplication implements CommandLineRunner {
	@Autowired
	private DenunciaRepository denunciaRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrabalhofinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Denuncia denuncia1 = new Denuncia(null, "EXCESSO DE LIMITE DE VELOCIDADE NA AV.PORTUGAL",
				"O MELIANTE AVANÇOU NA AV.PORTUGAL AS 16H DO DIA 31 DE JANEIRO DE 2020 A 160KM/H",
				"bit.ly/foto-rachador");

		Denuncia denuncia2 = new Denuncia(null, "EXCESSO DE LIMITE DE VELOCIDADE NA AV.PORTUGAL",
				"O MELIANTE AVANÇOU NA AV.PORTUGAL AS 16H DO DIA 31 DE JANEIRO DE 2020 A 160KM/H",
				"bit.ly/foto-rachador");

		denunciaRepository.saveAll(Arrays.asList(denuncia1, denuncia2));

	}
}
