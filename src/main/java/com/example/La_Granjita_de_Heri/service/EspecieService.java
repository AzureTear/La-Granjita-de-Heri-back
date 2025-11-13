package com.example.La_Granjita_de_Heri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.La_Granjita_de_Heri.repository.EspecieRepository;
import com.example.La_Granjita_de_Heri.model.Especie;


public class EspecieService {
    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> findAll() {
        return especieRepository.findAll();
    }

    public Especie findById(Integer id) {
        Especie especie = especieRepository.findById(id).orElse(null);
        return especie;
    }

    public Especie save(Especie especie) {
        return especieRepository.save(especie);
    }

    public Especie partialUpdate(Especie especie){
        Especie existingEspecie = especieRepository.findById(especie.getId()).orElse(null);
        if (existingEspecie != null) {
            if (especie.getNombre() != null) {
                existingEspecie.setNombre(especie.getNombre());
            }

            return especieRepository.save(existingEspecie);
        }
        return null;
    }

    public void deleteById(Integer id) {
        especieRepository.deleteById(id);
    }

}
