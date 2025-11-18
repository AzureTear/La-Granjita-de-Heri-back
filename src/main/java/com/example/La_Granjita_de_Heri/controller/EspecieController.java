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

import com.example.La_Granjita_de_Heri.service.EspecieService;
import com.example.La_Granjita_de_Heri.model.Especie;

@RestController
@RequestMapping("/api/especies")
public class EspecieController {
    
    @Autowired
    private EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<Especie>> getallEspecie() {
        List<Especie> especies = especieService.findAll();
        if (especies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(especies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Especie> getEspecieById(@PathVariable Integer id) {
        Especie especie = especieService.findById(id);
        if (especie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(especie);
    }

    @PostMapping
    public ResponseEntity<Especie> createEspecie(@RequestBody Especie especie) {
        especie.setId(null);
        Especie especieNew = especieService.save(especie);
        return ResponseEntity.status(201).body(especieNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especie> updateEspecie(@PathVariable Integer id, @RequestBody Especie especie) {
        especie.setId(id);
        Especie updatedEspecie = especieService.save(especie);
        if (updatedEspecie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEspecie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Especie> updateParcialEspecie(@PathVariable Integer id, @RequestBody Especie especie) {
        especie.setId(id);
        Especie updatedEspecie = especieService.partialUpdate(especie);
        if (updatedEspecie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEspecie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecie(@PathVariable Integer id) {
        especieService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}
