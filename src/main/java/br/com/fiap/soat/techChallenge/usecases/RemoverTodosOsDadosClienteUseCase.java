package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.RemoverTodosOsDadosUseCasePort;

import java.util.UUID;

public class RemoverTodosOsDadosClienteUseCase implements RemoverTodosOsDadosClienteUseCasePort {
    private final ClienteGatewayPort clienteGateway;

    public RemoverTodosOsDadosUseCase(ClienteGatewayPort clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void execute(UUID id) {
        return clienteGateway.removerTodosOsDados(id);
    }
}
