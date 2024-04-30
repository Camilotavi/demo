package com.example.demo.pruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.example.demo.entity.Recompensa;
import com.example.demo.repository.RecompensaRepository;
import com.example.demo.dto.RecompensaDTO;
import com.example.demo.service.RecompensaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecompensaServiceTest {

    @InjectMocks
    RecompensaService service;

    @Mock
    RecompensaRepository repository;

    @Test
    void DadoRecompensa_CuandoañadirRecompensa_EntoncesGuardaRecompensa(){
        Recompensa recompensa = new Recompensa();

        service.agregarRecompensa(recompensa);

        verify(repository).save(recompensa);
    }

    @Test
    void DadoRecompensas_CuandoSeUseFindAll_EntoncesDevuelveListaRecompensas() {
        List<Recompensa> recompensasPrueba = new ArrayList<>();
        recompensasPrueba.add(new Recompensa());
        recompensasPrueba.add(new Recompensa());

        when(repository.findAll()).thenReturn(recompensasPrueba);

        List<RecompensaDTO> recompensasDto = service.obtenerRecompensas();

        verify(repository).findAll();

        assertNotNull(recompensasDto);
        assertEquals(recompensasPrueba.size(), recompensasDto.size());
    }
}
