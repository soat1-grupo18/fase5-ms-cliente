package br.com.fiap.soat.techChallenge.usecases;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.CadastrarClienteUseCasePort;

public class CadastrarClienteUseCase implements CadastrarClienteUseCasePort {
    private final ClienteGatewayPort clienteGateway;

    public CadastrarClienteUseCase(ClienteGatewayPort clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente execute(Cliente cliente) {
        return clienteGateway.cadastra(cliente);
    }
}
