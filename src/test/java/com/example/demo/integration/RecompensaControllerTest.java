package com.example.demo.integration;

import com.example.demo.entity.Recompensa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class RecompensaControllerTest extends AbstractTest {

    String path = "/recompensa";

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void test_ajustarNombreRecompensa(){
        Recompensa recompensa = new Recompensa();
        recompensa.setId(2);
        recompensa.setNombre("iPhone16");
        ResponseEntity<String> response = restTemplate.postForEntity(path, recompensa, String.class);
        Assertions.assertEquals("Recompensa guardada", response.getBody());
    }

    @Test
    void test_ajustarDescripcionRecompensa(){
        Recompensa recompensa = new Recompensa();
        recompensa.setId(2);
        recompensa.setDescripcion("iPhone15 de 1TB");
        ResponseEntity<String> response = restTemplate.postForEntity(path, recompensa, String.class);
        Assertions.assertEquals("Recompensa guardada", response.getBody());
    }

    @Test
    void test_agregarRecompensa() {
        Recompensa recompensa = new Recompensa();
        recompensa.setCategoria("Tecno");
        recompensa.setNombre("Airpods Pro");
        recompensa.setDescripcion("Airpods Pro de última generación");
        recompensa.setPuntosRequeridos(20);
        ResponseEntity<String> response = restTemplate.postForEntity(path, recompensa, String.class);
        ResponseEntity<Recompensa> result = restTemplate.getForEntity(path + "/1", Recompensa.class);
        // Verificar que se ha insertado correctamente el recompensa
        Assertions.assertEquals("Airpods Pro", result.getBody().getNombre());
    }

    @Test
    void test_obtenerRecompensas() {
        ResponseEntity<List<Recompensa>> response = restTemplate.exchange(
                path + "s",  // Agrega la 's' para pluralizar la ruta
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recompensa>>() {});

        List<Recompensa> recompensas = response.getBody();
        Assertions.assertNotNull(recompensas);
        Assertions.assertFalse(recompensas.isEmpty());
    }


}
