package com.example.La_Granjita_de_Heri.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.La_Granjita_de_Heri.controllerV2.ProductoControllerV2;
import com.example.La_Granjita_de_Heri.model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>>{

    @SuppressWarnings("null")
    @Override
    public EntityModel<Producto> toModel(Producto producto){
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).getProductoById(Integer.valueOf(producto.getId()))).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).getAllProductoes()).withRel("Producto"),
                linkTo(methodOn(ProductoControllerV2.class).actualizarProducto(Integer.valueOf(producto.getId()), producto)).withRel("actualizar producto"),
                linkTo(methodOn(ProductoControllerV2.class).deleteProducto(Integer.valueOf(producto.getId()))).withRel("eliminar")
        );
    }
}