package com.example.La_Granjita_de_Heri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.La_Granjita_de_Heri.model.MetodoPago;
import com.example.La_Granjita_de_Heri.service.MetodoPagoService;

@RestController
@RequestMapping("/api/metodosPago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> getallMetodoEnvio() {
        List<MetodoPago> metodoPagos = metodoPagoService.findAll();
        if (metodoPagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodoPagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getMetodoPagoById(@PathVariable Integer id) {
        MetodoPago metodoPago = metodoPagoService.findById(id);
        if (metodoPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoPago);
    }

    
}
