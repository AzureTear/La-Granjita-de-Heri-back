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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.La_Granjita_de_Heri.assemblers.VentaModelAssembler;
import com.example.La_Granjita_de_Heri.model.Venta;
import com.example.La_Granjita_de_Heri.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/ventaes")
public class VentaControllerV2 {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler assembler;
    @Operation(summary = "Listar todos los ventaes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Venta>> getAllVentaes() {
        List<EntityModel<Venta>> venta = ventaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(venta,
                linkTo(methodOn(VentaControllerV2.class).getAllVentaes()).withSelfRel());
    }
    @Operation(summary = "Obtener un venta por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> getVentaById(@PathVariable Integer id){
        Venta venta = ventaService.findById(id);
        if (venta == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(venta));
    }
    @Operation(summary = "Crear un nuevo venta")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> createVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.save(venta);
        return ResponseEntity
                .created(linkTo(methodOn(VentaControllerV2.class).getVentaById(Integer.valueOf(nuevaVenta.getId()))).toUri())
                .body(assembler.toModel(nuevaVenta));
    }
    @Operation(summary = "Actualizar un venta existente")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> actualizarVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        venta.setId(id.intValue());
        Venta updated = ventaService.save(venta);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @Operation(summary = "Eliminar un venta por ID")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        Venta venta = ventaService.findById(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

