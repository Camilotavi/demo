package com.example.demo.pruebasUnitarias;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.example.demo.entity.*;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.RecompensaRepository;
import com.example.demo.repository.TransaccionRepository;
import com.example.demo.dto.TransaccionDTO;
import com.example.demo.service.TransaccionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransaccionServiceTest {

    @InjectMocks
    TransaccionService service;

    @Mock
    TransaccionRepository repository;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    RecompensaRepository recompensaRepository;

    @Mock
    PeticionRedimirPuntos peticion = new PeticionRedimirPuntos();

    @Mock
    Recompensa recompensa = new Recompensa();


    @Test
    void DadoPeticion_CuandoregistrarTransaccion_EntoncesGuardeTransaccion() {
        service.registrarTransaccion(peticion);

        Transaccion transaccion = new Transaccion(peticion.getClienteId(), peticion.getRecompensaId());

        verify(repository).save(transaccion);
    }

    @Test
    void DadoListaTransacciones_CuandoobtenerTransacciones_EntoncesDevuelvaTransacciones() {
        List<Transaccion> listaTransacciones = new ArrayList<>();
        listaTransacciones.add(new Transaccion(23, 23));
        listaTransacciones.add(new Transaccion(24, 24));

        when(repository.findAll()).thenReturn(listaTransacciones);

        List<Transaccion> prueba = service.obtenerTransacciones();

        assertNotNull(prueba);
    }

    @Test
    void DadoPeticionParaRedimirPuntos_CuandoredimirPuntosSuficientes_EntoncesClienteActualizaRecompensaYPuntos() {
        when(peticion.getClienteId()).thenReturn(0);
        when(peticion.getRecompensaId()).thenReturn(23);

        Cliente cliente = new Cliente("Andres", 23);
        when(recompensa.getPuntosRequeridos()).thenReturn(23);

        when(clienteRepository.findById(0)).thenReturn(Optional.of(cliente));
        when(recompensaRepository.findById(peticion.getRecompensaId())).thenReturn(Optional.of(recompensa));

        service.redimirPuntos(peticion);

        assertEquals(0, cliente.getPuntos());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void DadoPeticionParaRedimirPuntos_CuandoredimirPuntosInsuficientes_EntoncesClienteActualizaRecompensaYPuntos() {
        when(peticion.getClienteId()).thenReturn(0);
        when(peticion.getRecompensaId()).thenReturn(23);

        Cliente cliente = new Cliente("Andres", 9);
        when(recompensa.getPuntosRequeridos()).thenReturn(10);

        when(clienteRepository.findById(0)).thenReturn(Optional.of(cliente));
        when(recompensaRepository.findById(peticion.getRecompensaId())).thenReturn(Optional.of(recompensa));

        service.redimirPuntos(peticion);

        assertEquals(9, cliente.getPuntos());
        verify(clienteRepository, never()).save(cliente);
    }

    @Test
    public void DadoClienteId_CuandoobtenerTransaccionesPorIdClienteExistente_EntoncesDevuelveListaTransacciones() {
        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(0, 0));
        transacciones.add(new Transaccion(1, 1));
        transacciones.add(new Transaccion(0, 0));
        when(service.obtenerTransacciones()).thenReturn(transacciones);

        when(recompensaRepository.findById(0)).thenReturn(Optional.of(recompensa));

        List<TransaccionDTO> transaccionesCliente = service.obtenerTransaccionesPorIdCliente(0);

        assertNotNull(transaccionesCliente);
    }

    @Test
    public void DadoClienteId_CuandoobtenerTransaccionesPorIdClienteNoExistente_EntoncesDevuelveListaTransacciones() {
        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(2, 0));
        transacciones.add(new Transaccion(1, 1));
        transacciones.add(new Transaccion(1, 0));
        when(service.obtenerTransacciones()).thenReturn(transacciones);


        List<TransaccionDTO> transaccionesCliente = service.obtenerTransaccionesPorIdCliente(0);

        assertEquals(0, transaccionesCliente.size());
    }

    @Test
    public void DadoTransaccion_CuandoconvertirATransaccionDto_EntoncesDevuelveDTO() {
        // Crear una instancia de Transaccion
        Transaccion transaccion = new Transaccion();
        transaccion.setIdRecompensa(0); // Supongamos que la transacción tiene ID de recompensa 1

        when(recompensa.getPuntosRequeridos()).thenReturn(50);

        // Configurar el comportamiento del repositorio de recompensas mock
        when(recompensaRepository.findById(transaccion.getIdRecompensa())).thenReturn(Optional.of(recompensa));

        // Llamar al método que queremos probar
        TransaccionDTO transaccionDto = service.convertirATransaccionDto(transaccion);

        // Verificar que el TransaccionDTO resultante sea el esperado
        assertNotNull(transaccionDto);
    }

}
