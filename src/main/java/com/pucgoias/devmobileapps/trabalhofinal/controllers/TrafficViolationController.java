package com.pucgoias.devmobileapps.trabalhofinal.controllers;


import com.pucgoias.devmobileapps.trabalhofinal.models.TrafficViolation;
import com.pucgoias.devmobileapps.trabalhofinal.services.TrafficViolationService;
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
@RequestMapping("api/v1/traffic-violation")
public class TrafficViolationController {

    @Autowired
    private TrafficViolationService trafficViolationService;

    @ApiOperation(value = "Retorna uma lista de denúncias")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de denúncias cadastradas"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrafficViolation>> listTrafficViolations() {
        List<TrafficViolation> trafficViolations = trafficViolationService.getAll();
        return ResponseEntity.ok().body(trafficViolations);
    }


    @ApiOperation(value = "Permite a alteração de denúncias de infrações já existente")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Alteração realizada com sucesso"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PatchMapping(value="/violation")
    public ResponseEntity<Void> updateTrafficViolation(@RequestBody TrafficViolation trafficViolation){
        TrafficViolation obj = trafficViolationService.updateViolation(trafficViolation);
        obj.setId(trafficViolation.getId());
        return ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Insere uma denúncia de infração com imagem associada.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Insere uma nova denúncia de infração com imagem"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @PostMapping(value = "/save",
                consumes = {
                        MediaType.MULTIPART_FORM_DATA_VALUE,
                        MediaType.APPLICATION_JSON_VALUE
                },
                produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> insertTrafficViolation(
            @RequestPart(name="file") MultipartFile file,
            @RequestPart(name = "json") String denuncia) {

        URI uri = trafficViolationService.makeImageUpload(file, denuncia);
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Permite a exclusão de denúncias de infrações de trânsito existentes")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Exclusão realizada com sucesso"),
            @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerado um comportamento inesperado"),
    })
    @DeleteMapping(value = "/violation/{id}")
    public ResponseEntity<Void> deleteTrafficViolation(@PathVariable Integer id) {
        trafficViolationService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
