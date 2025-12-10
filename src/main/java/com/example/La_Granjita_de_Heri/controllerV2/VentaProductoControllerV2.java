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

import com.example.La_Granjita_de_Heri.assemblers.VentaProductoModelAssembler;
import com.example.La_Granjita_de_Heri.model.VentaProducto;
import com.example.La_Granjita_de_Heri.service.VentaProductoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/ventaproductos")
public class VentaProductoControllerV2 {

    @Autowired
    private VentaProductoService ventaproductoService;

    @Autowired
    private VentaProductoModelAssembler assembler;
    @Operation(summary = "Listar todos los ventaproductoes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<VentaProducto>> getAllVentaProductoes() {
        List<EntityModel<VentaProducto>> ventaproducto = ventaproductoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(ventaproducto,
                linkTo(methodOn(VentaProductoControllerV2.class).getAllVentaProductoes()).withSelfRel());
    }
    @Operation(summary = "Obtener un ventaproducto por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<VentaProducto>> getVentaProductoById(@PathVariable Integer id){
        VentaProducto ventaproducto = ventaproductoService.findById(id);
        if (ventaproducto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(ventaproducto));
    }
    @Operation(summary = "Crear un nuevo ventaproducto")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<VentaProducto>> createVentaProducto(@RequestBody VentaProducto ventaproducto) {
        VentaProducto nuevaVentaProducto = ventaproductoService.save(ventaproducto);
        return ResponseEntity
                .created(linkTo(methodOn(VentaProductoControllerV2.class).getVentaProductoById(Integer.valueOf(nuevaVentaProducto.getId()))).toUri())
                .body(assembler.toModel(nuevaVentaProducto));
    }
    @Operation(summary = "Actualizar un ventaproducto existente")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<VentaProducto>> actualizarVentaProducto(@PathVariable Integer id, @RequestBody VentaProducto ventaproducto) {
        ventaproducto.setId(id.intValue());
        VentaProducto updated = ventaproductoService.save(ventaproducto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @Operation(summary = "Eliminar un ventaproducto por ID")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteVentaProducto(@PathVariable Integer id) {
        VentaProducto ventaproducto = ventaproductoService.findById(id);
        if (ventaproducto == null) {
            return ResponseEntity.notFound().build();
        }
        ventaproductoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
