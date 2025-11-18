package com.example.La_Granjita_de_Heri.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.La_Granjita_de_Heri.model.MetodoEnvio;
import com.example.La_Granjita_de_Heri.repository.MetodoEnvioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MetodoEnvioService {
    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    public List<MetodoEnvio> findAll() {
        List<MetodoEnvio> metodoEnvios = metodoEnvioRepository.findAll();
        return metodoEnvios;
    }

    public MetodoEnvio findById(Integer id) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);
        return metodoEnvio;
    }

    public MetodoEnvio updateMetodoEnvio(MetodoEnvio metodoEnvio) {
        return save(metodoEnvio);
    }

    public MetodoEnvio save(MetodoEnvio metodoEnvio) {
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public void deleteById(Integer id) {
        metodoEnvioRepository.deleteById(id);
    }
}
