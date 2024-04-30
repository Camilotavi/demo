package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idCliente;

    private int idRecompensa;

    public Transaccion(int idCliente, int idRecompensa) {
        this.idCliente = idCliente;
        this.idRecompensa = idRecompensa;
    }

    public Transaccion() {
    }
}
