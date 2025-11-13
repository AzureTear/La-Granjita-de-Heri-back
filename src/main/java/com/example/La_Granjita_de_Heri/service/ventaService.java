package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.La_Granjita_de_Heri.model.Venta;
import com.example.La_Granjita_de_Heri.repository.VentaRepository;

@Service
@Transactional
public class ventaService {

    @Autowired
    private VentaRepository ventaRepository;

    
    public Venta save(Venta venta) {
    return ventaRepository.save(venta);
    }

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }
}
