package com.pucgoias.devmobileapps.trabalhofinal.services;

import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.DenunciaRepository;
import com.pucgoias.devmobileapps.trabalhofinal.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
