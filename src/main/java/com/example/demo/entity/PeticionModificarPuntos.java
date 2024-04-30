package com.example.demo.entity;

import lombok.Getter;

@Getter
public class PeticionModificarPuntos {

    private int idPersona;
    private int cantidadPuntos;

    public PeticionModificarPuntos() {
    }

    public PeticionModificarPuntos(Cliente cliente, int cantidadPuntos){
        this.idPersona = cliente.getId();
        this.cantidadPuntos = cantidadPuntos;
    }
}
