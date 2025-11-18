package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.La_Granjita_de_Heri.model.Raza;
import com.example.La_Granjita_de_Heri.repository.RazaRepository;

public class RazaService {
    @Autowired
    private RazaRepository razaRepository;

        public List<Raza> findAll() {
        return razaRepository.findAll();
    }

    public Raza findById(Integer id) {
        Raza raza = razaRepository.findById(id).orElse(null);
        return raza;
    }

    public Raza save(Raza raza) {
        return razaRepository.save(raza);
    }

    public Raza partialUpdate(Raza raza){
        Raza existingRaza = razaRepository.findById(raza.getId()).orElse(null);
        if (existingRaza != null) {
            if (raza.getNombre() != null) {
                existingRaza.setNombre(raza.getNombre());
            }

            return razaRepository.save(existingRaza);
        }
        return null;
    }

    public void deleteById(Integer id) {
        razaRepository.deleteById(id);
    }
}
