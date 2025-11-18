package com.example.La_Granjita_de_Heri.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.La_Granjita_de_Heri.model.Estado;
import com.example.La_Granjita_de_Heri.repository.EstadoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    @SuppressWarnings("null")
    public Estado findById(Integer id) {
        return estadoRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    } 
    
}
