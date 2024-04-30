package com.example.demo.controller;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.PeticionModificarPuntos;
import com.example.demo.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Cliente;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/cliente")
    public String agregarCliente(@RequestBody Cliente cliente) {
        service.anadirCliente(cliente);
        return "Cliente guardado";
    }

    @GetMapping("/cliente/{id}")
    public ClienteDTO obtenerClientePorId(@PathVariable int id){
        return this.service.obtenerClientePorId(id);
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> obtenerTodosClientes(){
        return service.obtenerClientes();
    }

    @PutMapping("/cliente/puntos")
    public String aumentarPuntosClienteEspecifico(@RequestBody PeticionModificarPuntos peticionModificarPuntos){
        service.modificarPuntos(peticionModificarPuntos);
        return "Puntos modificados correctamente";
    }


}
