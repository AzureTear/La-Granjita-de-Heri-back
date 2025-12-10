package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.MetodoEnvioControllerV2;
import com.example.La_Granjita_de_Heri.model.MetodoEnvio;

@Component
public class MetodoEnvioModelAssembler implements RepresentationModelAssembler<MetodoEnvio, EntityModel<MetodoEnvio>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<MetodoEnvio> toModel(MetodoEnvio metodoenvio){
        return EntityModel.of(metodoenvio,
                linkTo(methodOn(MetodoEnvioControllerV2.class).getMetodoEnvioById(Integer.valueOf(metodoenvio.getId()))).withSelfRel(),
                linkTo(methodOn(MetodoEnvioControllerV2.class).getAllMetodoEnvioes()).withRel("MetodoEnvio"),
                linkTo(methodOn(MetodoEnvioControllerV2.class).actualizarMetodoEnvio(Integer.valueOf(metodoenvio.getId()), metodoenvio)).withRel("actualizar metodoenvio"),
                linkTo(methodOn(MetodoEnvioControllerV2.class).deleteMetodoEnvio(Integer.valueOf(metodoenvio.getId()))).withRel("eliminar")
        );
    }
}