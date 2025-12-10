package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.UsuarioControllerV2;
import com.example.La_Granjita_de_Heri.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario){
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(Integer.valueOf(usuario.getId()))).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarioes()).withRel("Usuario"),
                linkTo(methodOn(UsuarioControllerV2.class).actualizarUsuario(Integer.valueOf(usuario.getId()), usuario)).withRel("actualizar usuario"),
                linkTo(methodOn(UsuarioControllerV2.class).deleteUsuario(Integer.valueOf(usuario.getId()))).withRel("eliminar")
        );
    }
}