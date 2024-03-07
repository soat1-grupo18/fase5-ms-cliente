package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.RemoverTodosOsDadosClienteUseCasePort;

import java.util.UUID;

public class RemoverTodosOsDadosClienteUseCase implements RemoverTodosOsDadosClienteUseCasePort {
    private final ClienteGatewayPort clienteGateway;

    public RemoverTodosOsDadosClienteUseCase(ClienteGatewayPort clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void execute(UUID id) {
        clienteGateway.removerTodosOsDados(id);
    }
}
