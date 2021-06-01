package com.pucgoias.devmobileapps.trabalhofinal.controllers;


import com.pucgoias.devmobileapps.trabalhofinal.models.Denuncia;
import com.pucgoias.devmobileapps.trabalhofinal.services.DenunciaService;
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
@RequestMapping("api/v1/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @ApiOperation(value = "Retorna uma lista de denúncias")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de denúncias cadastradas"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Denuncia>> listarDenuncias () {
        List<Denuncia> denuncias = denunciaService.obterTodas();
        return ResponseEntity.ok().body(denuncias);
    }


    @ApiOperation(value = "Permite a alteração de denúncias de infrações já existente")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Alteração realizada com sucesso"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PatchMapping(value="/denuncia")
    public ResponseEntity<Void> alterarDenuncia( @RequestBody Denuncia denuncia){
        Denuncia obj = denunciaService.alterarDenuncia(denuncia);
        obj.setId(denuncia.getId());
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Permite a inserção de denúncias de infrações de trânsito sem imagem associada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere uma nova denúncia de infração sem imagem"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PostMapping(value = "/salvar")
    public ResponseEntity<Denuncia> inserirDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia denunciaAInserir = denunciaService.salvar(denuncia);
        return ResponseEntity.ok().body(denunciaAInserir);
    }

    @ApiOperation(value = "Insere uma denúncia de infração com imagem associada.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere uma nova denúncia de infração com imagem"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PostMapping(value = "/imagem",
                consumes = {
                        MediaType.MULTIPART_FORM_DATA_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                },
                produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> uploadImagemDenuncia(
            @RequestPart(name="file") MultipartFile file,
            @RequestPart(name = "json") String denuncia) {

        URI uri = denunciaService.realizaUploadDeImagemDaDenuncia(file, denuncia);
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Permite a exclusão de denúncias de infrações de trânsito existentes")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exclusão realizada com sucesso"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirDenuncia(@PathVariable Integer id) {
        denunciaService.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

}
