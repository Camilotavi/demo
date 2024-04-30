package com.example.demo.dto;

import com.example.demo.entity.Recompensa;
import lombok.Getter;

@Getter
public class RecompensaDTO {

    private String categoria;
    private String nombre;
    private String descripcion;
    private int puntosRequeridos;

    public RecompensaDTO(Recompensa recompensa) {
        this.categoria = recompensa.getCategoria();
        this.nombre = recompensa.getNombre();
        this.descripcion = recompensa.getDescripcion();
        this.puntosRequeridos = recompensa.getPuntosRequeridos();
    }
}
