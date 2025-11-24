package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.La_Granjita_de_Heri.model.Carrito;
import com.example.La_Granjita_de_Heri.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CarritoService {
    

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    @SuppressWarnings("null")
    public Carrito findById(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    } 

    public Carrito partialUpdate(Carrito carrito){
        Carrito existingCarrito = carritoRepository.findById(carrito.getId()).orElse(null);
        if (existingCarrito != null) {
            if (carrito.getCantidad() != null) {
                existingCarrito.setCantidad(carrito.getCantidad());
            }

            return carritoRepository.save(existingCarrito);
        }
        return null;
    }

    public void deleteById(Integer id) {
        carritoRepository.deleteById(id);
    }
    
}
