package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.La_Granjita_de_Heri.model.VentaProducto;
import com.example.La_Granjita_de_Heri.repository.VentaProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class VentaProductoService {

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    public VentaProducto save(VentaProducto ventaProducto) {
        return ventaProductoRepository.save(ventaProducto);
    } 

    public VentaProducto findById(Integer id) {
        return ventaProductoRepository.findById(id).orElse(null);
    }

    public List<VentaProducto> findAll() {
        return ventaProductoRepository.findAll();
    }


    public VentaProducto partialUpdate(VentaProducto ventaProducto){
        VentaProducto existingVenta = ventaProductoRepository.findById(ventaProducto.getId()).orElse(null);
        if (existingVenta != null) {
            if (ventaProducto.getProducto() != null) {
                existingVenta.setProducto((ventaProducto.getProducto()));
            }

            if (ventaProducto.getVenta() != null) {
                existingVenta.setVenta((ventaProducto.getVenta()));
            }

            return ventaProductoRepository.save(existingVenta);
        }
        return null;
    }

    public void deleteById(Integer id) {
        ventaProductoRepository.deleteById(id);
    }
    
}
