package com.example.demo.service;

import com.example.demo.dto.TransaccionDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.PeticionRedimirPuntos;
import com.example.demo.entity.Recompensa;
import com.example.demo.entity.Transaccion;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.RecompensaRepository;
import com.example.demo.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final RecompensaRepository recompensaRepository;

    private final ClienteRepository clienteRepository;

    public TransaccionService(TransaccionRepository transaccionRepository, RecompensaRepository recompensaRepository, ClienteRepository clienteRepository) {
        this.transaccionRepository = transaccionRepository;
        this.recompensaRepository = recompensaRepository;
        this.clienteRepository = clienteRepository;
    }

    public void registrarTransaccion(PeticionRedimirPuntos peticionRedimirPuntos) {
        this.transaccionRepository.save(new Transaccion(peticionRedimirPuntos.getClienteId(), peticionRedimirPuntos.getRecompensaId()));
    }

    public List<Transaccion> obtenerTransacciones() {
        return this.transaccionRepository.findAll();
    }

    public void redimirPuntos(PeticionRedimirPuntos peticionRedimirPuntos) {
        Optional<Cliente> cliente = clienteRepository.findById(peticionRedimirPuntos.getClienteId());
        Optional<Recompensa> recompensa = recompensaRepository.findById(peticionRedimirPuntos.getRecompensaId());

        Cliente clienteObtenido = cliente.get();
        Recompensa recompensaObtenida = recompensa.get();

        if (clienteObtenido.getPuntos() >= recompensaObtenida.getPuntosRequeridos()) {
            this.registrarTransaccion(peticionRedimirPuntos);
            clienteObtenido.setPuntos(clienteObtenido.getPuntos() - recompensaObtenida.getPuntosRequeridos());
            clienteRepository.save(clienteObtenido);
        }
    }

    public List<TransaccionDTO> obtenerTransaccionesPorIdCliente(int idCliente) {
        List<Transaccion> transacciones = this.obtenerTransacciones();
        List<TransaccionDTO> transaccionesCliente = new ArrayList<TransaccionDTO>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getIdCliente() == idCliente) {
                TransaccionDTO transaccionDto = this.convertirATransaccionDto(transaccion);
                transaccionesCliente.add(transaccionDto);
            }
        }
        return transaccionesCliente;
    }

    public TransaccionDTO convertirATransaccionDto(Transaccion transaccion) {
        Optional<Recompensa> recompensa = this.recompensaRepository.findById(transaccion.getIdRecompensa());
        TransaccionDTO transaccionDto = new TransaccionDTO(transaccion.getIdCliente(), recompensa.get());
        return transaccionDto;
    }


}
