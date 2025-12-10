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

import com.example.La_Granjita_de_Heri.assemblers.MetodoEnvioModelAssembler;
import com.example.La_Granjita_de_Heri.model.MetodoEnvio;
import com.example.La_Granjita_de_Heri.service.MetodoEnvioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v2/metodoenvios")
public class MetodoEnvioControllerV2 {

    @Autowired
    private MetodoEnvioService metodoenvioService;

    @Autowired
    private MetodoEnvioModelAssembler assembler;
    @Operation(summary = "Listar todos los metodoenvioes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<MetodoEnvio>> getAllMetodoEnvioes() {
        List<EntityModel<MetodoEnvio>> metodoenvio = metodoenvioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(metodoenvio,
                linkTo(methodOn(MetodoEnvioControllerV2.class).getAllMetodoEnvioes()).withSelfRel());
    }
    @Operation(summary = "Obtener un metodoenvio por ID")
    @GetMapping(value = "/{id}",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoEnvio>> getMetodoEnvioById(@PathVariable Integer id){
        MetodoEnvio metodoenvio = metodoenvioService.findById(id);
        if (metodoenvio == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(metodoenvio));
    }
    @Operation(summary = "Crear un nuevo metodoenvio")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoEnvio>> createMetodoEnvio(@RequestBody MetodoEnvio metodoenvio) {
        MetodoEnvio nuevaMetodoEnvio = metodoenvioService.save(metodoenvio);
        return ResponseEntity
                .created(linkTo(methodOn(MetodoEnvioControllerV2.class).getMetodoEnvioById(Integer.valueOf(nuevaMetodoEnvio.getId()))).toUri())
                .body(assembler.toModel(nuevaMetodoEnvio));
    }
    @Operation(summary = "Actualizar un metodoenvio existente")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoEnvio>> actualizarMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoenvio) {
        metodoenvio.setId(id.intValue());
        MetodoEnvio updated = metodoenvioService.save(metodoenvio);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updated));
    }
    @Operation(summary = "Eliminar un metodoenvio por ID")
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteMetodoEnvio(@PathVariable Integer id) {
        MetodoEnvio metodoenvio = metodoenvioService.findById(id);
        if (metodoenvio == null) {
            return ResponseEntity.notFound().build();
        }
        metodoenvioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
