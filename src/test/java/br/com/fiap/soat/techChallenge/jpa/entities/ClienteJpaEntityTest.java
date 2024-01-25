package br.com.fiap.soat.techChallenge.jpa.entities;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteJpaEntityTest {

    @Test
    void testToDomain() {
        // Dados de exemplo
        UUID id = UUID.randomUUID();
        String nome = "John Doe";
        String cpf = "123.456.789-01";
        String telefone = "123-456-789";

        ClienteJpaEntity clienteJpaEntity = new ClienteJpaEntity();
        clienteJpaEntity.setId(id);
        clienteJpaEntity.setNome(nome);
        clienteJpaEntity.setCpf(cpf);
        clienteJpaEntity.setTelefone(telefone);

        // Executar o método toDomain
        Cliente cliente = clienteJpaEntity.toDomain();

        // Verificar se os dados são os esperados
        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(telefone, cliente.getTelefone());
    }

    @Test
    void testFromDomain() {
        // Dados de exemplo
        UUID id = UUID.randomUUID();
        String nome = "Jane Doe";
        String cpf = "987.654.321-09";
        String telefone = "987-654-321";

        Cliente cliente = new Cliente(id, nome, cpf, telefone);

        // Executar o método fromDomain
        ClienteJpaEntity clienteJpaEntity = ClienteJpaEntity.fromDomain(cliente);

        // Verificar se os dados são os esperados
        //assertEquals(id, clienteJpaEntity.getId());
        assertEquals(nome, clienteJpaEntity.getNome());
        assertEquals(cpf, clienteJpaEntity.getCpf());
        assertEquals(telefone, clienteJpaEntity.getTelefone());
    }
}
