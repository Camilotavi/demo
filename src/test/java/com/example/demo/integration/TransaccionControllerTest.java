package com.example.demo.integration;

import com.example.demo.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Objects;

public class TransaccionControllerTest extends AbstractTest {

    String path = "/transaccion";

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void test_agregarTransaccion() {
        PeticionRedimirPuntos peticionRedimirPuntos = new PeticionRedimirPuntos();
        peticionRedimirPuntos.setClienteId(3);
        peticionRedimirPuntos.setRecompensaId(2);
        ResponseEntity<String> response = restTemplate.postForEntity(path, peticionRedimirPuntos, String.class);
        Assertions.assertEquals("Transaccion guardada", Objects.requireNonNull(response.getBody()));
    }

    @Test
    void test_obtenerTransacciones() {
        ResponseEntity<List<Transaccion>> response = restTemplate.exchange(
                path + "es",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaccion>>() {});

        List<Transaccion> transacciones = response.getBody();
        Assertions.assertNotNull(transacciones);
        Assertions.assertFalse(transacciones.isEmpty());
    }
}
