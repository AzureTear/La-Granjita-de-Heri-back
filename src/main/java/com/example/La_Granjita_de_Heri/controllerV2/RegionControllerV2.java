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


import com.example.La_Granjita_de_Heri.assemblers.RegionModelAssembler;

import com.example.La_Granjita_de_Heri.model.Region;
import com.example.La_Granjita_de_Heri.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/regiones")
public class RegionControllerV2 {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionModelAssembler assembler;
    @Operation(summary = "Listar todas las regiones")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Region>> getAllRegiones() {
        List<EntityModel<Region>> region = regionService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(region,
                linkTo(methodOn(RegionControllerV2.class).getAllRegiones()).withSelfRel());
    }
    @Operation(summary = "Obtener una region por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Region>> getRegionById(@PathVariable Integer id){
        Region region = regionService.findById(id);
        if (region == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(region));
    }
    @Operation(summary = "Crear una nueva region")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Region>> createRegion(@RequestBody Region region) {
        Region nuevaRegion = regionService.save(region);
        return ResponseEntity
                .created(linkTo(methodOn(RegionControllerV2.class).getRegionById(Integer.valueOf(nuevaRegion.getId()))).toUri())
                .body(assembler.toModel(nuevaRegion));
    }

}