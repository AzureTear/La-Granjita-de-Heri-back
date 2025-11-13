package com.example.La_Granjita_de_Heri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.La_Granjita_de_Heri.model.Raza;
@Repository

public interface RazaRepository extends JpaRepository<Raza, Integer>{
    
}
