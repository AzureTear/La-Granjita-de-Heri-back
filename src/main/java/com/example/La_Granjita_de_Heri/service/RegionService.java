package com.example.La_Granjita_de_Heri.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.La_Granjita_de_Heri.model.Region;
import com.example.La_Granjita_de_Heri.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @SuppressWarnings("null")
    public Region findById(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Region save(Region region) {
        return regionRepository.save(region);
    } 
    
}
