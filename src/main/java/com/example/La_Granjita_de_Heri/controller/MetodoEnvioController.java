package com.example.La_Granjita_de_Heri.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.La_Granjita_de_Heri.model.MetodoEnvio;
import com.example.La_Granjita_de_Heri.service.MetodoEnvioService;

@RestController
@RequestMapping("/api/metodosEnvio")
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @GetMapping
    public ResponseEntity<List<MetodoEnvio>> getallMetodoEnvio() {
        List<MetodoEnvio> metodoEnvios = metodoEnvioService.findAll();
        if (metodoEnvios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(metodoEnvios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MetodoEnvio> getMetodoEnvioById(@PathVariable Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioService.findById(id);
        if (metodoEnvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoEnvio);
    }

    @PostMapping
    public ResponseEntity<MetodoEnvio> createMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        metodoEnvio.setId(null);
        MetodoEnvio metodoEnvioNew = metodoEnvioService.save(metodoEnvio);
        return ResponseEntity.status(201).body(metodoEnvioNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoEnvio> updateMetodoEnvio(@PathVariable Integer id, @RequestBody MetodoEnvio metodoEnvio) {
        metodoEnvio.setId(id);
        MetodoEnvio updatedMetodoEnvio = metodoEnvioService.save(metodoEnvio);
        if (updatedMetodoEnvio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMetodoEnvio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoEnvio(@PathVariable Integer id) {
        metodoEnvioService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }        
}
