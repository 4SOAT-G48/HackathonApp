package br.com.fiap.soat4.grupo48.telemed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Grupo48ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void applicationStartsSuccessfully() {
        Grupo48Application.main(new String[]{});
        // Assuming the application should start and be accessible
        String body = this.restTemplate.getForObject("/", String.class);
        assertEquals("Expected response", body);
    }
}
