package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.RolControllerV2;
import com.example.La_Granjita_de_Heri.model.Rol;

@Component
public class RolModelAssembler implements RepresentationModelAssembler<Rol, EntityModel<Rol>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Rol> toModel(Rol rol){
        return EntityModel.of(rol,
                linkTo(methodOn(RolControllerV2.class).getRolById(Integer.valueOf(rol.getId()))).withSelfRel(),
                linkTo(methodOn(RolControllerV2.class).getAllRoles()).withRel("Rol")
        );
    }
}
