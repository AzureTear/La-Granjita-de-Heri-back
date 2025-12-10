package com.example.La_Granjita_de_Heri.controllerV2;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.La_Granjita_de_Heri.assemblers.EspecieModelAssembler;

import com.example.La_Granjita_de_Heri.model.Especie;
import com.example.La_Granjita_de_Heri.service.EspecieService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/especies")
public class EspecieControllerV2 {

    @Autowired
    private EspecieService especieService;

    @Autowired
    private EspecieModelAssembler assembler;
    @Operation(summary = "Listar todas las especiees")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Especie>> getAllEspeciees() {
        List<EntityModel<Especie>> especie = especieService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(especie,
                linkTo(methodOn(EspecieControllerV2.class).getAllEspeciees()).withSelfRel());
    }
    @Operation(summary = "Obtener una especie por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Especie>> getEspecieById(@PathVariable Integer id){
        Especie especie = especieService.findById(id);
        if (especie == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(especie));
    }
    @Operation(summary = "Crear una nueva especie")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Especie>> createEspecie(@RequestBody Especie especie) {
        Especie nuevaEspecie = especieService.save(especie);
        return ResponseEntity
                .created(linkTo(methodOn(EspecieControllerV2.class).getEspecieById(Integer.valueOf(nuevaEspecie.getId()))).toUri())
                .body(assembler.toModel(nuevaEspecie));
    }

}

