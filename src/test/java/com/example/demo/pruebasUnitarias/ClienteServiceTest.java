package com.example.demo.pruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.PeticionModificarPuntos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    ClienteService service;

    @Mock
    ClienteRepository repository;

    @Mock
    PeticionModificarPuntos peticion = new PeticionModificarPuntos();

    @Test
    void DadoCliente_CuandoañadirCliente_EntoncesGuardarCliente() {
        Cliente cliente = new Cliente("prueba", 99);

        service.anadirCliente(cliente);

        verify(repository).save(cliente);
    }

    @Test
    void DadoClientes_CuandoSeUseFindall_EntoncesDevuelveListaClientes() {
        List<Cliente> clientesPrueba = new ArrayList<>();
        clientesPrueba.add(new Cliente("Andres", 21));
        clientesPrueba.add(new Cliente("Andrea", 23));

        when(repository.findAll()).thenReturn(clientesPrueba);

        List<ClienteDTO> clientesDto = service.obtenerClientes();

        verify(repository).findAll();

        assertNotNull(clientesDto);
        assertEquals(clientesPrueba.size(), clientesDto.size());
    }

    @Test
    void DadoPeticionSumaPuntos_CuandoSeVerifiqueCliente_EntoncesSeGuardenLosPuntos() {

        when(peticion.getCantidadPuntos()).thenReturn(50);

        Cliente cliente = new Cliente("prueba", 99);

        when(repository.findById(0)).thenReturn(Optional.of(cliente));

        service.modificarPuntos(peticion);

        verify(repository).findById(0);

        verify(repository).save(cliente);

        assertEquals(149, cliente.getPuntos());

    }

    @Test
    void DadoPeticionSumaPuntos_CuandoSeVerifiqueNoCliente_EntoncesNoSeGuardenLosPuntos() {

        Cliente cliente = new Cliente();

        when(repository.findById(0)).thenReturn(Optional.empty());

        when(repository.findById(peticion.getIdPersona())).thenReturn(Optional.empty());

        service.modificarPuntos(peticion);

        verify(repository).findById(peticion.getIdPersona());

        verify(repository, never()).save(cliente);

    }

}
