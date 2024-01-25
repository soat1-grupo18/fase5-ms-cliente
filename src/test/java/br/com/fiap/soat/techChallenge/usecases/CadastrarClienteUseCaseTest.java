package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CadastrarClienteUseCaseTest {

    private ClienteGatewayPort clienteGateway;
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(ClienteGatewayPort.class);
        cadastrarClienteUseCase = new CadastrarClienteUseCase(clienteGateway);
    }

    @Test
    void testExecute() {
        // Dados de exemplo
        Cliente clienteEntrada = new Cliente("John Doe", "123.456.789-01", "123-456-789");
        Cliente clienteRetorno = new Cliente(); // TODO

        // Configurando o comportamento do mock
        when(clienteGateway.cadastra(clienteEntrada)).thenReturn(clienteRetorno);

        // Executando o caso de uso
        Cliente resultado = cadastrarClienteUseCase.execute(clienteEntrada);

        // Verificando se o método do gateway foi chamado com os parâmetros corretos
        verify(clienteGateway, times(1)).cadastra(clienteEntrada);

        // Verificando se o resultado é o esperado
        assertEquals(clienteRetorno, resultado);
    }
}
