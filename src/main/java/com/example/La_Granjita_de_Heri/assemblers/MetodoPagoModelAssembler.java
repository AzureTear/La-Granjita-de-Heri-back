package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.MetodoPagoControllerV2;
import com.example.La_Granjita_de_Heri.model.MetodoPago;

@Component
public class MetodoPagoModelAssembler implements RepresentationModelAssembler<MetodoPago, EntityModel<MetodoPago>>{
 
    @SuppressWarnings("null")
    @Override
    public EntityModel<MetodoPago> toModel(MetodoPago metodopago){
        return EntityModel.of(metodopago,
                linkTo(methodOn(MetodoPagoControllerV2.class).getMetodoPagoById(Integer.valueOf(metodopago.getId()))).withSelfRel(),
                linkTo(methodOn(MetodoPagoControllerV2.class).getAllMetodoPagoes()).withRel("MetodoPago"),
                linkTo(methodOn(MetodoPagoControllerV2.class).actualizarMetodoPago(Integer.valueOf(metodopago.getId()), metodopago)).withRel("actualizar metodopago"),
                linkTo(methodOn(MetodoPagoControllerV2.class).deleteMetodoPago(Integer.valueOf(metodopago.getId()))).withRel("eliminar")
        );
    }
}
