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

import com.example.La_Granjita_de_Heri.service.VentaProductoService;
import com.example.La_Granjita_de_Heri.model.VentaProducto;

@RestController
@RequestMapping("/api/ventaproductos")
public class VentaProductoController {
    
    @Autowired
    private VentaProductoService ventaproductoService;

    @GetMapping
    public ResponseEntity<List<VentaProducto>> getallVentaProducto() {
        List<VentaProducto> ventaproductos = ventaproductoService.findAll();
        if (ventaproductos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventaproductos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VentaProducto> getVentaProductoById(@PathVariable Integer id) {
        VentaProducto ventaproducto = ventaproductoService.findById(id);
        if (ventaproducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventaproducto);
    }

    @PostMapping
    public ResponseEntity<VentaProducto> createVentaProducto(@RequestBody VentaProducto ventaproducto) {
        ventaproducto.setId(null);
        VentaProducto ventaproductoNew = ventaproductoService.save(ventaproducto);
        return ResponseEntity.status(201).body(ventaproductoNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaProducto> updateVentaProducto(@PathVariable Integer id, @RequestBody VentaProducto ventaproducto) {
        ventaproducto.setId(id);
        VentaProducto updatedVentaProducto = ventaproductoService.save(ventaproducto);
        if (updatedVentaProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVentaProducto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VentaProducto> updateParcialVentaProducto(@PathVariable Integer id, @RequestBody VentaProducto ventaproducto) {
        ventaproducto.setId(id);
        VentaProducto updatedVentaProducto = ventaproductoService.partialUpdate(ventaproducto);
        if (updatedVentaProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVentaProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentaProducto(@PathVariable Integer id) {
        ventaproductoService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}
