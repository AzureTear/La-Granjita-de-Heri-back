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


import com.example.La_Granjita_de_Heri.assemblers.EstadoModelAssembler;

import com.example.La_Granjita_de_Heri.model.Estado;
import com.example.La_Granjita_de_Heri.service.EstadoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/estados")
public class EstadoControllerV2 {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoModelAssembler assembler;
    @Operation(summary = "Listar todas las estadoes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Estado>> getAllEstadoes() {
        List<EntityModel<Estado>> estado = estadoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(estado,
                linkTo(methodOn(EstadoControllerV2.class).getAllEstadoes()).withSelfRel());
    }
    @Operation(summary = "Obtener una estado por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Estado>> getEstadoById(@PathVariable Integer id){
        Estado estado = estadoService.findById(id);
        if (estado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(estado));
    }
    @Operation(summary = "Crear una nueva estado")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Estado>> createEstado(@RequestBody Estado estado) {
        Estado nuevaEstado = estadoService.save(estado);
        return ResponseEntity
                .created(linkTo(methodOn(EstadoControllerV2.class).getEstadoById(Integer.valueOf(nuevaEstado.getId()))).toUri())
                .body(assembler.toModel(nuevaEstado));
    }

}
