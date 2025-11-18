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

import com.example.La_Granjita_de_Heri.service.ProductoService;
import com.example.La_Granjita_de_Heri.model.Producto;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getallProducto() {
        List<Producto> productos = productoService.findAll();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        producto.setId(null);
        Producto productoNew = productoService.save(producto);
        return ResponseEntity.status(201).body(productoNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto updatedProducto = productoService.save(producto);
        if (updatedProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProducto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> updateParcialProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto updatedProducto = productoService.partialUpdate(producto);
        if (updatedProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}
