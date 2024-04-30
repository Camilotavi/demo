package com.example.demo.dto;

import com.example.demo.entity.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    private String nombre;
    private int puntos;

    public ClienteDTO(Cliente cliente) {
        this.nombre = cliente.getNombre();
        this.puntos = cliente.getPuntos();
    }

    public ClienteDTO(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }
}
