package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import com.example.La_Granjita_de_Heri.controllerV2.ComunaControllerV2;
import com.example.La_Granjita_de_Heri.model.Comuna;

@Component
public class ComunaModelAssembler implements RepresentationModelAssembler<Comuna, EntityModel<Comuna>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Comuna> toModel(Comuna comuna){
        return EntityModel.of(comuna,
                linkTo(methodOn(ComunaControllerV2.class).getComunaById(Integer.valueOf(comuna.getId()))).withSelfRel(),
                linkTo(methodOn(ComunaControllerV2.class).getAllComunaes()).withRel("Comuna")
        );
    }
}
