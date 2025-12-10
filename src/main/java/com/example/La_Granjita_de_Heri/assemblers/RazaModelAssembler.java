package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.RazaControllerV2;
import com.example.La_Granjita_de_Heri.model.Raza;

@Component
public class RazaModelAssembler implements RepresentationModelAssembler<Raza, EntityModel<Raza>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Raza> toModel(Raza raza){
        return EntityModel.of(raza,
                linkTo(methodOn(RazaControllerV2.class).getRazaById(Integer.valueOf(raza.getId()))).withSelfRel(),
                linkTo(methodOn(RazaControllerV2.class).getAllRazaes()).withRel("Raza")
        );
    }
}


