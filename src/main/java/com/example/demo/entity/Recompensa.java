package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Table
@Entity
@Data
@AllArgsConstructor
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String categoria;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private int puntosRequeridos;

    public Recompensa() {
    }

    public Recompensa(String categoria, String nombre, String descripcion, int puntosRequeridos) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.puntosRequeridos = puntosRequeridos;
    }
}
