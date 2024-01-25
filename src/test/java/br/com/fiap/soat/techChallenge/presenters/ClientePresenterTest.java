package br.com.fiap.soat.techChallenge.presenters;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ClientePresenterTest {

    @Test
    void testFromDomain() {
        // Dados de exemplo
        UUID id = UUID.randomUUID();
        String nome = "John Doe";
        String cpf = "123.456.789-01";
        String telefone = "123-456-789";

        Cliente cliente = new Cliente(id, nome, cpf, telefone);

        // Criar um ClientePresenter usando o método fromDomain
        ClientePresenter clientePresenter = ClientePresenter.fromDomain(cliente);

        // Verificar se os dados são os esperados
        assertEquals(id, clientePresenter.getId());
        assertEquals(nome, clientePresenter.getNome());
        assertEquals(cpf, clientePresenter.getCpf());
        assertEquals(telefone, clientePresenter.getTelefone());
    }

    @Test
    void testSettersAndGetters() {
        // Dados de exemplo
        UUID id = UUID.randomUUID();
        String nome = "Jane Doe";
        String cpf = "987.654.321-09";
        String telefone = "987-654-321";

        // Criar um ClientePresenter usando o construtor
        ClientePresenter clientePresenter = new ClientePresenter(id, nome, cpf, telefone);

        // Verificar se os dados são os esperados usando os getters
        assertEquals(id, clientePresenter.getId());
        assertEquals(nome, clientePresenter.getNome());
        assertEquals(cpf, clientePresenter.getCpf());
        assertEquals(telefone, clientePresenter.getTelefone());

        // Modificar os dados usando setters
        UUID novoId = UUID.randomUUID();
        String novoNome = "Bob";
        String novoCpf = "111.222.333-44";
        String novoTelefone = "555-666-777";

        clientePresenter.setId(novoId);
        clientePresenter.setNome(novoNome);
        clientePresenter.setCpf(novoCpf);
        clientePresenter.setTelefone(novoTelefone);

        // Verificar se os dados foram modificados corretamente
        assertEquals(novoId, clientePresenter.getId());
        assertEquals(novoNome, clientePresenter.getNome());
        assertEquals(novoCpf, clientePresenter.getCpf());
        assertEquals(novoTelefone, clientePresenter.getTelefone());
    }
}
