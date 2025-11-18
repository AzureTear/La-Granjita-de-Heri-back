package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.La_Granjita_de_Heri.model.Producto;
import com.example.La_Granjita_de_Heri.repository.ProductoRepository;

public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        List<Producto> Producto = productoRepository.findAll();
        return Producto;
    }

    @SuppressWarnings("null")
    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto partialUpdate(Producto producto) {
        Producto existingProducto = productoRepository.findById(producto.getId()).orElse(null);

        if (existingProducto != null) {

            if (producto.getNombre() != null) {
                existingProducto.setNombre(producto.getNombre());
            }

            if (producto.getPrecio() != null) {
                existingProducto.setPrecio(producto.getPrecio());
            }

            if (producto.getRaza() != null) {
                existingProducto.setRaza(producto.getRaza());
            }

            return productoRepository.save(existingProducto);
        }

        return null;
    }



    public void deleteById(Integer id) {
        productoRepository.deleteById(id);;
    }

}
