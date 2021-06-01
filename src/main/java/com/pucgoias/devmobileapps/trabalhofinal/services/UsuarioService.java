package com.pucgoias.devmobileapps.trabalhofinal.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pucgoias.devmobileapps.trabalhofinal.exceptions.ObjectNotFoundException;
import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.models.Usuario;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.DenunciaRepository;
import com.pucgoias.devmobileapps.trabalhofinal.repositories.UsuarioRepository;
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
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obterTodas() {
       return usuarioRepository.findAll();
    }

    public Usuario obterDenunciaPorId(Integer id) {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
