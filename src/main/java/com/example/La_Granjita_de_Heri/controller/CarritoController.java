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

import com.example.La_Granjita_de_Heri.service.CarritoService;
import com.example.La_Granjita_de_Heri.model.Carrito;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    
    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> getallCarrito() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Integer id) {
        Carrito carrito = carritoService.findById(id);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<Carrito> createCarrito(@RequestBody Carrito carrito) {
        carrito.setId(null);
        Carrito carritoNew = carritoService.save(carrito);
        return ResponseEntity.status(201).body(carritoNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> updateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        Carrito updatedCarrito = carritoService.save(carrito);
        if (updatedCarrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCarrito);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carrito> updateParcialCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        Carrito updatedCarrito = carritoService.partialUpdate(carrito);
        if (updatedCarrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCarrito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Integer id) {
        carritoService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}

