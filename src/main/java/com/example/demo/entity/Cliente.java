package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Table
@Entity
@Data
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private int puntos;

    public Cliente(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public Cliente(int id, String nombre, int puntos) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public Cliente() {
    }
}
