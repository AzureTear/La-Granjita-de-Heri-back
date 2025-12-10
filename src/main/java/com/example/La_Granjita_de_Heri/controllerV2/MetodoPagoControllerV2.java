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

import com.example.La_Granjita_de_Heri.assemblers.MetodoPagoModelAssembler;
import com.example.La_Granjita_de_Heri.model.MetodoPago;
import com.example.La_Granjita_de_Heri.service.MetodoPagoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/metodopagos")
public class MetodoPagoControllerV2 {

    @Autowired
    private MetodoPagoService metodopagoService;

    @Autowired
    private MetodoPagoModelAssembler assembler;
    @Operation(summary = "Listar todos los metodopagoes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<MetodoPago>> getAllMetodoPagoes() {
        List<EntityModel<MetodoPago>> metodopago = metodopagoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(metodopago,
                linkTo(methodOn(MetodoPagoControllerV2.class).getAllMetodoPagoes()).withSelfRel());
    }
    @Operation(summary = "Obtener un metodopago por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> getMetodoPagoById(@PathVariable Integer id){
        MetodoPago metodopago = metodopagoService.findById(id);
        if (metodopago == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(metodopago));
    }
    @Operation(summary = "Crear un nuevo metodopago")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> createMetodoPago(@RequestBody MetodoPago metodopago) {
        MetodoPago nuevaMetodoPago = metodopagoService.save(metodopago);
        return ResponseEntity
                .created(linkTo(methodOn(MetodoPagoControllerV2.class).getMetodoPagoById(Integer.valueOf(nuevaMetodoPago.getId()))).toUri())
                .body(assembler.toModel(nuevaMetodoPago));
    }
    @Operation(summary = "Actualizar un metodopago existente")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> actualizarMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodopago) {
        metodopago.setId(id.intValue());
        MetodoPago updated = metodopagoService.save(metodopago);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }
    @Operation(summary = "Eliminar un metodopago por ID")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable Integer id) {
        MetodoPago metodopago = metodopagoService.findById(id);
        if (metodopago == null) {
            return ResponseEntity.notFound().build();
        }
        metodopagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
