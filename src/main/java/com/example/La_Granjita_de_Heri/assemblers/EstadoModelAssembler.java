package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.EstadoControllerV2;
import com.example.La_Granjita_de_Heri.model.Estado;

@Component
public class EstadoModelAssembler implements RepresentationModelAssembler<Estado, EntityModel<Estado>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Estado> toModel(Estado estado){
        return EntityModel.of(estado,
                linkTo(methodOn(EstadoControllerV2.class).getEstadoById(Integer.valueOf(estado.getId()))).withSelfRel(),
                linkTo(methodOn(EstadoControllerV2.class).getAllEstadoes()).withRel("Estado")
        );
    }
}
