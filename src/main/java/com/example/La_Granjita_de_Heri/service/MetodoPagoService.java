package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.La_Granjita_de_Heri.model.MetodoPago;
import com.example.La_Granjita_de_Heri.repository.MetodoPagoRepository;

public class MetodoPagoService {
    
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;


    @SuppressWarnings("null")
    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    } 

    @SuppressWarnings("null")
    public MetodoPago findById(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }


    public MetodoPago updateMetodoPagoRepository(MetodoPago metodoPago) {
        return save(metodoPago);
    }


    public void deleteById(Integer id) {
        metodoPagoRepository.deleteById(id);
    }


}
