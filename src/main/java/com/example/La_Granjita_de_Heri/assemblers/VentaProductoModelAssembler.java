package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.VentaProductoControllerV2;
import com.example.La_Granjita_de_Heri.model.VentaProducto;

@Component
public class VentaProductoModelAssembler implements RepresentationModelAssembler<VentaProducto, EntityModel<VentaProducto>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<VentaProducto> toModel(VentaProducto ventaproducto){
        return EntityModel.of(ventaproducto,
                linkTo(methodOn(VentaProductoControllerV2.class).getVentaProductoById(Integer.valueOf(ventaproducto.getId()))).withSelfRel(),
                linkTo(methodOn(VentaProductoControllerV2.class).getAllVentaProductoes()).withRel("VentaProducto"),
                linkTo(methodOn(VentaProductoControllerV2.class).actualizarVentaProducto(Integer.valueOf(ventaproducto.getId()), ventaproducto)).withRel("actualizar ventaproducto"),
                linkTo(methodOn(VentaProductoControllerV2.class).deleteVentaProducto(Integer.valueOf(ventaproducto.getId()))).withRel("eliminar")
        );
    }
}
