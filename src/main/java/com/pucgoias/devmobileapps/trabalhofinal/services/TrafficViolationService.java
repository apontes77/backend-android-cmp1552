package com.pucgoias.devmobileapps.trabalhofinal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pucgoias.devmobileapps.trabalhofinal.models.TrafficViolation;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.TrafficViolationRepository;
import com.pucgoias.devmobileapps.trabalhofinal.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Esta classe acessa os métodos de acesso e manipulação dee banco de dados e realiza operações associadas.
 * Funciona como uma "ponte" entre as requisições realizadas nos endpoints na classe Controller e
 * os métodos de persistência.
 */

@Service
public class TrafficViolationService {
    @Autowired
    private TrafficViolationRepository trafficViolationRepository;
    @Autowired
    private S3Service s3Service;

    public List<TrafficViolation> getAll() {
       return trafficViolationRepository.findAll();
    }

    public TrafficViolation getViolationsByID(Integer id) {
        Optional<TrafficViolation> obj = trafficViolationRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TrafficViolation.class.getName()));
    }

    public TrafficViolation updateViolation(TrafficViolation trafficViolation) {
        TrafficViolation novaTrafficViolation = getViolationsByID(trafficViolation.getId());
        updateData(novaTrafficViolation, trafficViolation);
        return trafficViolationRepository.save(novaTrafficViolation);
    }

    public TrafficViolation save(TrafficViolation trafficViolation) {
        return trafficViolationRepository.save(trafficViolation);
    }

    public void deleteByID(Integer id) {
        trafficViolationRepository.deleteById(id);
    }

    private void updateData(TrafficViolation newObj, TrafficViolation obj) {
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setPhoto(obj.getPhoto());
    }

    public URI makeImageUpload(MultipartFile multipartFile, String denunciaAEnviar) {
        URI uri = s3Service.uploadFile(multipartFile);
        TrafficViolation trafficViolationJson = new TrafficViolation();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            trafficViolationJson = objectMapper.readValue(denunciaAEnviar, TrafficViolation.class);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        trafficViolationJson.setPhoto(uri.toString());

        this.save(trafficViolationJson);
        return uri;
    }
}
