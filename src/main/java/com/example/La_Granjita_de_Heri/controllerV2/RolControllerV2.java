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

import com.example.La_Granjita_de_Heri.assemblers.RolModelAssembler;
import com.example.La_Granjita_de_Heri.model.Rol;
import com.example.La_Granjita_de_Heri.service.RolService;


import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2/roles")
public class RolControllerV2 {

    @Autowired
    private RolService rolService;

    @Autowired 
    private RolModelAssembler assembler;
    @Operation(summary = "Listar todos los roles")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Rol>> getAllRoles() {
        List<EntityModel<Rol>> rol = rolService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(rol,
                linkTo(methodOn(RolControllerV2.class).getAllRoles()).withSelfRel());
    }
    @Operation(summary = "Obtener un rol por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Rol>> getRolById(@PathVariable Integer id){
        Rol rol = rolService.findById(id);
        if (rol == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(rol));
    }
    @Operation(summary = "Crear un nuevo rol")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Rol>> createRol(@RequestBody Rol rol) {
        Rol nuevaRol = rolService.save(rol);
        return ResponseEntity
                .created(linkTo(methodOn(RolControllerV2.class).getRolById(Integer.valueOf(nuevaRol.getId()))).toUri())
                .body(assembler.toModel(nuevaRol));
    }




}