package com.example.La_Granjita_de_Heri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.La_Granjita_de_Heri.model.Comuna;


@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
    
}
