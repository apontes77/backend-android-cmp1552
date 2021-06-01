package com.pucgoias.devmobileapps.trabalhofinal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.DenunciaRepository;
import com.pucgoias.devmobileapps.trabalhofinal.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Esta classe acessa os métodos de acesso e manipulação dee banco de dados e realiza operações associadas.
 * Funciona como uma "ponte" entre as requisições realizadas nos endpoints na classe Controller e
 * os métodos de persistência.
 */

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private S3Service s3Service;

    public List<Denuncia> obterTodas() {
       return denunciaRepository.findAll();
    }

    public Denuncia obterDenunciaPorId(Integer id) {
        Optional<Denuncia> obj = denunciaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Denuncia.class.getName()));
    }

    public Denuncia alterarDenuncia(Denuncia denuncia) {
        Denuncia novaDenuncia = obterDenunciaPorId(denuncia.getId());
        atualizarDados(novaDenuncia, denuncia);
        return denunciaRepository.save(novaDenuncia);
    }

    public Denuncia salvar(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public void excluirPorId(Integer id) {
        denunciaRepository.deleteById(id);
    }

    private void atualizarDados(Denuncia newObj, Denuncia obj) {
        newObj.setTituloDenuncia(obj.getTituloDenuncia());
        newObj.setCorpoDenuncia(obj.getCorpoDenuncia());
        newObj.setUrlFotoDenuncia(obj.getUrlFotoDenuncia());
    }

    public URI realizaUploadDeImagemDaDenuncia(MultipartFile multipartFile, String denunciaAEnviar) {
        URI uri = s3Service.uploadFile(multipartFile);
        Denuncia denunciaJson = new Denuncia();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            denunciaJson = objectMapper.readValue(denunciaAEnviar, Denuncia.class);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        denunciaJson.setUrlFotoDenuncia(uri.toString());
        this.salvar(denunciaJson);
        return uri;
    }
}
