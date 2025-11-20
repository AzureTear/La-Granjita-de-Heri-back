package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.La_Granjita_de_Heri.model.Venta;
import com.example.La_Granjita_de_Heri.repository.VentaRepository;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    
    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    } 

    public Venta findById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta partialUpdate(Venta venta){
        Venta existingVenta = ventaRepository.findById(venta.getId()).orElse(null);
        if (existingVenta != null) {
            if (venta.getMetodoEnvio() != null) {
                existingVenta.setMetodoEnvio((venta.getMetodoEnvio()));
            }

            return ventaRepository.save(existingVenta);
        }
        return null;
    }

    public void deleteById(Integer id) {
        ventaRepository.deleteById(id);
    }
}
