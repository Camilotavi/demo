package com.example.demo.service;

import com.example.demo.dto.RecompensaDTO;
import com.example.demo.entity.Recompensa;
import com.example.demo.repository.RecompensaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecompensaService {

    private final RecompensaRepository repository;

    public RecompensaService(RecompensaRepository repository) {
        this.repository = repository;
    }

    public void agregarRecompensa(Recompensa recompensa) {
        repository.save(recompensa);
    }

    public List<RecompensaDTO> obtenerRecompensas() {
        List<Recompensa> recompensas = repository.findAll();
        List<RecompensaDTO> recompensasDto = new ArrayList<RecompensaDTO>();
        for (Recompensa recompensa : recompensas) {
            recompensasDto.add(new RecompensaDTO(recompensa));
        }
        return recompensasDto;
    }

    public RecompensaDTO obternerRecompensaPorId(int id) {
        RecompensaDTO recompensaDTO = null;
        Optional<Recompensa> recompensa = repository.findById(id);
        if (recompensa.isPresent()) {
            recompensaDTO = new RecompensaDTO(recompensa.get());
        }
        return recompensaDTO;
    }


}
