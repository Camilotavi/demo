package com.example.demo.dto;

import com.example.demo.entity.Recompensa;
import lombok.Getter;

@Getter

public class TransaccionDTO {

    private int idCliente;
    private String nombreRecompensa;
    private int puntosRequeridos;

    public TransaccionDTO(int idCliente, Recompensa recompensa){
        this.idCliente = idCliente;
        this.nombreRecompensa = recompensa.getNombre();
        this.puntosRequeridos = recompensa.getPuntosRequeridos();
    }

}
