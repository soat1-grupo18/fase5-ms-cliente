package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdentificarClienteUseCaseTest {

    private ClienteGatewayPort clienteGateway;
    private IdentificarClienteUseCase identificarClienteUseCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(ClienteGatewayPort.class);
        identificarClienteUseCase = new IdentificarClienteUseCase(clienteGateway);
    }

    @Test
    void testExecuteClienteExistente() {
        // Dados de exemplo
        String cpf = "123.456.789-01";
        Cliente clienteRetorno = new Cliente("John Doe", cpf, "123-456-789");

        // Configurando o comportamento do mock
        when(clienteGateway.identificaPor(cpf)).thenReturn(Optional.of(clienteRetorno));

        // Executando o caso de uso
        Optional<Cliente> resultado = identificarClienteUseCase.execute(cpf);

        // Verificando se o método do gateway foi chamado com os parâmetros corretos
        verify(clienteGateway, times(1)).identificaPor(cpf);

        // Verificando se o resultado é o esperado
        assertTrue(resultado.isPresent());
        assertEquals(clienteRetorno, resultado.get());
    }

    @Test
    void testExecuteClienteNaoExistente() {
        // Dados de exemplo
        String cpf = "111.222.333-44";

        // Configurando o comportamento do mock
        when(clienteGateway.identificaPor(cpf)).thenReturn(Optional.empty());

        // Executando o caso de uso
        Optional<Cliente> resultado = identificarClienteUseCase.execute(cpf);

        // Verificando se o método do gateway foi chamado com os parâmetros corretos
        verify(clienteGateway, times(1)).identificaPor(cpf);

        // Verificando se o resultado é vazio
        assertFalse(resultado.isPresent());
    }
}
