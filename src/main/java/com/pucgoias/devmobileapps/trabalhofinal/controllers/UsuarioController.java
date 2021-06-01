package com.pucgoias.devmobileapps.trabalhofinal.controllers;


import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.models.Usuario;
import com.pucgoias.devmobileapps.trabalhofinal.services.DenunciaService;
import com.pucgoias.devmobileapps.trabalhofinal.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

/**
 *
 * Esta classe expõe o uso de verbos HTTP por meio de métodos que permitem criar, alterar, listar e excluir denuncias.
 */

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Retorna uma lista de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de usuários cadastrados"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> listarUsuarios () {
        List<Usuario> usuarios = usuarioService.obterTodas();
        return ResponseEntity.ok().body(usuarios);
    }

    @ApiOperation(value = "Permite a inserção de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere novo usuário "),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PostMapping(value = "/salvar")
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAInserir = usuarioService.salvar(usuario);
        return ResponseEntity.ok().body(usuarioAInserir);
    }
}
