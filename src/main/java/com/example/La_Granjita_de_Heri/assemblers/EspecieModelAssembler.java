package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.EspecieControllerV2;
import com.example.La_Granjita_de_Heri.model.Especie;

@Component
public class EspecieModelAssembler implements RepresentationModelAssembler<Especie, EntityModel<Especie>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Especie> toModel(Especie especie){
        return EntityModel.of(especie,
                linkTo(methodOn(EspecieControllerV2.class).getEspecieById(Integer.valueOf(especie.getId()))).withSelfRel(),
                linkTo(methodOn(EspecieControllerV2.class).getAllEspeciees()).withRel("Especie")
        );
    }
}


