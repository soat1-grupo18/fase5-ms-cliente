package br.com.fiap.soat.techChallenge.exceptions;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteNaoEncontradoExceptionTest {

    @Test
    void testCriacaoAPartirDoCpf() {
        // Dado
        String cpf = "123.456.789-01";

        // Quando
        ClienteNaoEncontradoException exception = ClienteNaoEncontradoException.aPartirDoCpf(cpf);

        // Então
        assertEquals("O cliente de CPF 123.456.789-01 não foi encontrado.", exception.getMessage());
    }

    @Test
    void testCriacaoAPartirDoId() {
        // Dado
        UUID id = UUID.randomUUID();

        // Quando
        ClienteNaoEncontradoException exception = ClienteNaoEncontradoException.aPartirDoId(id);

        // Então
        assertEquals(String.format("O cliente de ID %s não foi encontrado.", id), exception.getMessage());
    }
}
