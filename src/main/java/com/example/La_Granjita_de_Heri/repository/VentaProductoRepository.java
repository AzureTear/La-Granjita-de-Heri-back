package com.example.La_Granjita_de_Heri.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.La_Granjita_de_Heri.model.VentaProducto;

@Repository
public interface VentaProductoRepository extends JpaRepository<VentaProducto, Integer> {

    
} 
