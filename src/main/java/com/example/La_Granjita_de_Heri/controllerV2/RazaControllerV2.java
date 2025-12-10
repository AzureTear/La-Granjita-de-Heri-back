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


import com.example.La_Granjita_de_Heri.assemblers.RazaModelAssembler;

import com.example.La_Granjita_de_Heri.model.Raza;
import com.example.La_Granjita_de_Heri.service.RazaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/razas")
public class RazaControllerV2 {

    @Autowired
    private RazaService razaService;

    @Autowired
    private RazaModelAssembler assembler;
    @Operation(summary = "Listar todas las razaes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Raza>> getAllRazaes() {
        List<EntityModel<Raza>> raza = razaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(raza,
                linkTo(methodOn(RazaControllerV2.class).getAllRazaes()).withSelfRel());
    }
    @Operation(summary = "Obtener una raza por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Raza>> getRazaById(@PathVariable Integer id){
        Raza raza = razaService.findById(id);
        if (raza == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(raza));
    }
    @Operation(summary = "Crear una nueva raza")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Raza>> createRaza(@RequestBody Raza raza) {
        Raza nuevaRaza = razaService.save(raza);
        return ResponseEntity
                .created(linkTo(methodOn(RazaControllerV2.class).getRazaById(Integer.valueOf(nuevaRaza.getId()))).toUri())
                .body(assembler.toModel(nuevaRaza));
    }

}
