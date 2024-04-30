package com.example.demo.controller;

import com.example.demo.dto.TransaccionDTO;
import com.example.demo.entity.PeticionRedimirPuntos;
import com.example.demo.entity.Transaccion;
import com.example.demo.service.TransaccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransaccionController {

    private final TransaccionService service;

    public TransaccionController(TransaccionService service) {
        this.service = service;
    }

    @PostMapping("/transaccion")
    public String redimirPuntos(@RequestBody PeticionRedimirPuntos peticionRedimirPuntos) {
        service.redimirPuntos(peticionRedimirPuntos);
        return "Transaccion guardada";
    }

    @GetMapping("/transacciones")
    public List<Transaccion> obtenerTodosClientes(){
        return service.obtenerTransacciones();
    }

    @GetMapping("/transacciones/cliente/{id}")
    public List<TransaccionDTO> obtenerTransaccionesPorIdCliente(@PathVariable int id){
        return this.service.obtenerTransaccionesPorIdCliente(id);
    }
}
