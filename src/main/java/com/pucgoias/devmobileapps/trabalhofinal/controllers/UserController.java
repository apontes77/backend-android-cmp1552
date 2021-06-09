package com.pucgoias.devmobileapps.trabalhofinal.controllers;


import com.pucgoias.devmobileapps.trabalhofinal.models.User;
import com.pucgoias.devmobileapps.trabalhofinal.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Esta classe expõe o uso de verbos HTTP por meio de métodos que permitem criar, alterar, listar e excluir denuncias.
 */

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Retorna uma lista de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de usuários cadastrados"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login (@RequestBody User user) {

        final boolean isLogged = userService.login(user);
        if (isLogged) {
            return ResponseEntity.ok().build();
        }
       return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Permite a inserção de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere novo usuário "),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PostMapping(value = "/save")
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        User userToInsert = userService.saveUser(user);
        return ResponseEntity.ok().body(userToInsert);
    }
}
