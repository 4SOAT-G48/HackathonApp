package br.com.fiap.soat4.grupo48.telemed.cadastro.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminTest {

    @Test
    void getterReturnsCorrectId() {
        UUID id = UUID.randomUUID();
        Admin admin = new Admin();
        admin.setId(id);
        admin.setNome("Admin");
        admin.setEmail("admin@testdev.com.br");
        assertEquals(id, admin.getId());
    }

    @Test
    void getterReturnsCorrectName() {
        Admin admin = new Admin();
        UUID id = UUID.randomUUID();
        admin.setId(id);
        admin.setNome("Admin");
        admin.setEmail("admin@testdev.com.br");
        assertEquals("Admin", admin.getNome());
    }

    @Test
    void getterReturnsCorrectEmail() {
        Admin admin = new Admin();
        UUID id = UUID.randomUUID();
        admin.setId(id);
        admin.setNome("Admin");
        admin.setEmail("admin@testdev.com.br");
        assertEquals("admin@testdev.com.br", admin.getEmail());
    }

    @Test
    void setterUpdatesId() {
        Admin admin = new Admin();
        UUID id = UUID.randomUUID();
        admin.setId(id);
        admin.setNome("Admin");
        admin.setEmail("admin@testdev.com.br");
        UUID newId = UUID.randomUUID();
        admin.setId(newId);
        assertEquals(newId, admin.getId());
    }

    @Test
    void setterUpdatesName() {
        Admin admin = new Admin();
        UUID id = UUID.randomUUID();
        admin.setId(id);
        admin.setNome("Admin");
        admin.setEmail("admin@testdev.com.br");
        admin.setNome("New Admin");
        assertEquals("New Admin", admin.getNome());
    }

    @Test
    void setterUpdatesEmail() {
        Admin admin = new Admin();
        UUID id = UUID.randomUUID();
        admin.setId(id);
        admin.setNome("Admin");
        admin.setEmail("admin@testdev.com.br");
        admin.setEmail("newadmin@testdev.com.br");
        assertEquals("newadmin@testdev.com.br", admin.getEmail());
    }

}