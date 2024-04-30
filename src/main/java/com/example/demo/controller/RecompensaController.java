package com.example.demo.controller;

import com.example.demo.dto.RecompensaDTO;
import com.example.demo.entity.Recompensa;
import com.example.demo.service.RecompensaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class RecompensaController {

    private final RecompensaService service;

    public RecompensaController(RecompensaService service) {
        this.service = service;
    }

    @PostMapping("/recompensa")
    public String agregarRecompensa(@RequestBody Recompensa recompensa) {
        service.agregarRecompensa(recompensa);
        return "Recompensa guardada";
    }

    @GetMapping("/recompensa/{id}")
    public RecompensaDTO obtenerRecompensaPorId(@PathVariable int id){
        return service.obternerRecompensaPorId(id);
    }

    @GetMapping("/recompensas")
    public List<RecompensaDTO> obtenerRecompensas(){
        return service.obtenerRecompensas();
    }


}
