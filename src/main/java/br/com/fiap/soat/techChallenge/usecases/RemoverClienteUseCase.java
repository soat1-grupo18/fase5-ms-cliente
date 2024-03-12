package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.RemoverClienteUseCasePort;

import java.util.UUID;

public class RemoverClienteUseCase implements RemoverClienteUseCasePort {
    private final ClienteGatewayPort clienteGateway;

    public RemoverClienteUseCase(ClienteGatewayPort clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void execute(UUID id) {
        clienteGateway.removerCliente(id);
    }
}
