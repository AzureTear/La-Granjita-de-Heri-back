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

import com.example.La_Granjita_de_Heri.service.RazaService;
import com.example.La_Granjita_de_Heri.model.Raza;

@RestController
@RequestMapping("/api/razas")
public class RazaController {
    
    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<List<Raza>> getallRaza() {
        List<Raza> razas = razaService.findAll();
        if (razas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(razas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Raza> getRazaById(@PathVariable Integer id) {
        Raza raza = razaService.findById(id);
        if (raza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(raza);
    }

    @PostMapping
    public ResponseEntity<Raza> createRaza(@RequestBody Raza raza) {
        raza.setId(null);
        Raza razaNew = razaService.save(raza);
        return ResponseEntity.status(201).body(razaNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raza> updateRaza(@PathVariable Integer id, @RequestBody Raza raza) {
        raza.setId(id);
        Raza updatedRaza = razaService.save(raza);
        if (updatedRaza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRaza);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Raza> updateParcialRaza(@PathVariable Integer id, @RequestBody Raza raza) {
        raza.setId(id);
        Raza updatedRaza = razaService.partialUpdate(raza);
        if (updatedRaza == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRaza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaza(@PathVariable Integer id) {
        razaService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}
