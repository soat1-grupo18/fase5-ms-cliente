package br.com.fiap.soat.techChallenge.entities;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testConstrutorPadrao() {
        Cliente cliente = new Cliente();
        assertNull(cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getCpf());
        assertNull(cliente.getTelefone());
    }

    @Test
    void testConstrutorComParametros() {
        String nome = "John Doe";
        String cpf = "123.456.789-01";
        String telefone = "123-456-789";

        Cliente cliente = new Cliente(nome, cpf, telefone);

        assertNull(cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(telefone, cliente.getTelefone());
    }

    @Test
    void testConstrutorComId() {
        UUID id = UUID.randomUUID();
        String nome = "Jane Doe";
        String cpf = "987.654.321-09";
        String telefone = "987-654-321";

        Cliente cliente = new Cliente(id, nome, cpf, telefone);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(telefone, cliente.getTelefone());
    }

    @Test
    void testSettersAndGetters() {
        Cliente cliente = new Cliente();

        UUID id = UUID.randomUUID();
        String nome = "Alice";
        String cpf = "111.222.333-44";
        String telefone = "999-888-777";

        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(telefone, cliente.getTelefone());
    }
}
