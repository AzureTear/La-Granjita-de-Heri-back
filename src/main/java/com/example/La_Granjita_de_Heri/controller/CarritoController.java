package com.example.La_Granjita_de_Heri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<Carrito> crearCarrito() {
        return ResponseEntity.ok(carritoService.crearCarrito());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Integer id) {
        Carrito carrito = carritoService.obtenerCarrito(id);
        return carrito != null ? ResponseEntity.ok(carrito) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{carritoId}/agregar/{productoId}")
    public ResponseEntity<Carrito> agregarProducto(
        @PathVariable Integer carritoId,
        @PathVariable Integer productoId
    ) {
        Carrito carrito = carritoService.agregarProducto(carritoId, productoId);
        return carrito != null ? ResponseEntity.ok(carrito) : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{carritoId}/cantidad/{itemId}")
    public ResponseEntity<Carrito> modificarCantidad(
        @PathVariable Integer carritoId,
        @PathVariable Integer itemId,
        @RequestBody Integer cantidad
    ) {
        Carrito carrito = carritoService.actualizarCantidad(carritoId, itemId, cantidad);
        return carrito != null ? ResponseEntity.ok(carrito) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{carritoId}/item/{itemId}")
    public ResponseEntity<Carrito> borrarItem(
        @PathVariable Integer carritoId,
        @PathVariable Integer itemId
    ) {
        Carrito carrito = carritoService.eliminarItem(carritoId, itemId);
        return carrito != null ? ResponseEntity.ok(carrito) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCarrito(@PathVariable Integer id) {
        carritoService.vaciarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}
