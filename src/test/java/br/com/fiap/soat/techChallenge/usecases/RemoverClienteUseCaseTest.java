package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoverClienteUseCaseTest {

    private ClienteGatewayPort clienteGateway;
    private RemoverClienteUseCase removerClienteUseCase;

    @BeforeEach
    void setUp() {
        clienteGateway = mock(ClienteGatewayPort.class);
        removerClienteUseCase = new RemoverClienteUseCase(clienteGateway);
    }

    @Test
    void testExecuteRemoverCliente() {
        UUID id = UUID.randomUUID();
        Cliente cliente = new Cliente("John Doe", "1111111111", "123-456-789");
        cliente.setId(id);
    
        doNothing().when(clienteGateway).removerCliente(id);  // Fix: use clienteGateway without invoking the method
    
        removerClienteUseCase.execute(id);
    
        verify(clienteGateway, times(1)).removerCliente(id);
    }
}
