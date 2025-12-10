package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.VentaControllerV2;
import com.example.La_Granjita_de_Heri.model.Venta;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Venta> toModel(Venta venta){
        return EntityModel.of(venta,
                linkTo(methodOn(VentaControllerV2.class).getVentaById(Integer.valueOf(venta.getId()))).withSelfRel(),
                linkTo(methodOn(VentaControllerV2.class).getAllVentaes()).withRel("Venta"),
                linkTo(methodOn(VentaControllerV2.class).actualizarVenta(Integer.valueOf(venta.getId()), venta)).withRel("actualizar venta"),
                linkTo(methodOn(VentaControllerV2.class).deleteVenta(Integer.valueOf(venta.getId()))).withRel("eliminar")
        );
    }
}