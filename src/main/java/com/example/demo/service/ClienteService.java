package com.example.demo.service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.PeticionModificarPuntos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void anadirCliente(Cliente cliente) {
        repository.save(cliente);
        System.out.println("Cliente guardado exitosamente");
    }

    public List<ClienteDTO> obtenerClientes() {
        List<Cliente> clientes = repository.findAll();
        List<ClienteDTO> clientesDto = new ArrayList<ClienteDTO>();
        for (Cliente cliente : clientes) {
            clientesDto.add(new ClienteDTO(cliente));
        }
        return clientesDto;
    }

    public ClienteDTO obtenerClientePorId(int id) {
        Optional<Cliente> cliente = this.repository.findById(id);
        ClienteDTO clienteDTO = null;
        if (cliente.isPresent()) {
            clienteDTO = new ClienteDTO(cliente.get());
        }
        return clienteDTO;

    }

    public void modificarPuntos(PeticionModificarPuntos peticionModificarPuntos) {
        Optional<Cliente> cliente = repository.findById(peticionModificarPuntos.getIdPersona());
        if (cliente.isPresent()) {
            Cliente clienteObtenido = cliente.get();
            clienteObtenido.setPuntos(clienteObtenido.getPuntos() + peticionModificarPuntos.getCantidadPuntos());
            repository.save(clienteObtenido);
        }

    }


}
