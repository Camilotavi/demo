package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionRedimirPuntos {

    private int clienteId;
    private int recompensaId;

    public PeticionRedimirPuntos() {
    }
}
