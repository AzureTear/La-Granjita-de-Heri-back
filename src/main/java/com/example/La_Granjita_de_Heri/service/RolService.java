package com.example.La_Granjita_de_Heri.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.La_Granjita_de_Heri.model.Rol;
import com.example.La_Granjita_de_Heri.repository.RolRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @SuppressWarnings("null")
    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    } 
    
}
