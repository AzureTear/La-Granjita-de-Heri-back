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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.La_Granjita_de_Heri.assemblers.ComunaModelAssembler;
import com.example.La_Granjita_de_Heri.model.Comuna;
import com.example.La_Granjita_de_Heri.service.ComunaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/comunas")
public class ComunaControllerV2 {

    @Autowired
    private ComunaService comunaService;

    @Autowired
    private ComunaModelAssembler assembler;

    @Operation(summary = "Listar todas las comunaes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Comuna>> getAllComunaes() {
        List<EntityModel<Comuna>> comuna = comunaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(comuna,
                linkTo(methodOn(ComunaControllerV2.class).getAllComunaes()).withSelfRel());
    }
    @Operation(summary = "Obtener una institución por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comuna>> getComunaById(@PathVariable Integer id){
        Comuna comuna = comunaService.findById(id);
        if (comuna == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(comuna));
    }
    @Operation(summary = "Crear una nueva institución")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Comuna>> createComuna(@RequestBody Comuna comuna) {
        Comuna nuevaComuna = comunaService.save(comuna);
        return ResponseEntity
                .created(linkTo(methodOn(ComunaControllerV2.class).getComunaById(Integer.valueOf(nuevaComuna.getId()))).toUri())
                .body(assembler.toModel(nuevaComuna));
    }


}
