package com.example.La_Granjita_de_Heri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.La_Granjita_de_Heri.service.VentaService;
import com.example.La_Granjita_de_Heri.model.Venta;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getallVenta() {
        List<Venta> ventas = ventaService.findAll();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Venta venta = ventaService.findById(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        venta.setId(null);
        Venta ventaNew = ventaService.save(venta);
        return ResponseEntity.status(201).body(ventaNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        venta.setId(id);
        Venta updatedVenta = ventaService.save(venta);
        if (updatedVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVenta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Venta> updateParcialVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        venta.setId(id);
        Venta updatedVenta = ventaService.partialUpdate(venta);
        if (updatedVenta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        ventaService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}
