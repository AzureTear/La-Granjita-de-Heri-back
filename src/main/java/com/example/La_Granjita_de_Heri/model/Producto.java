package com.example.La_Granjita_de_Heri.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    
    @Column(name = "nombreProducto", nullable = false, length = 30)
    private String nombre;

    @Column(name = "precioProducto", nullable = false)
    private Integer precio;
    
    @Column(name = "imagenProducto", nullable = true)
    private String imagenUrl;

    @Column(name = "descripcionProducto", nullable = false)
    private String descripcion;

    @Column(name = "stockProducto",nullable = true)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "raza_id", nullable=false)
    private Raza raza;

}
