package br.com.fiap.soat.techChallenge.controllers;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.soat.techChallenge.interfaces.usecases.CadastrarClienteUseCasePort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.IdentificarClienteUseCasePort;
import br.com.fiap.soat.techChallenge.presenters.ClientePresenter;

import java.util.UUID;

public class ClienteController {

    private final CadastrarClienteUseCasePort cadastrarClienteUseCase;
    private final IdentificarClienteUseCasePort identificarClienteUseCase;
    private final RemoverTodosOsDadosClienteUseCasePort removerTodosOsDadosClienteUseCase;

    public ClienteController(CadastrarClienteUseCasePort cadastrarClienteUseCase,
                             IdentificarClienteUseCasePort identificarClienteUseCase,
                             RemoverTodosOsDadosClienteUseCasePort removerTodosOsDadosClienteUseCase) {

        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.identificarClienteUseCase = identificarClienteUseCase;
        this.removerTodosOsDadosClienteUseCase = removerTodosOsDadosClienteUseCase;
    }

    public ClientePresenter identificarCliente(String cpf) {
        Cliente cliente = identificarClienteUseCase.execute(cpf).orElseThrow(() -> ClienteNaoEncontradoException.aPartirDoCpf(cpf));
        return ClientePresenter.fromDomain(cliente);
    }

    public ClientePresenter cadastrarCliente(Cliente cliente) {
        return ClientePresenter.fromDomain(cadastrarClienteUseCase.execute(cliente));
    }

    public ClientePresenter removerTodosOsDadosCliente(UUID id) {
        return ClientePresenter.fromDomain(removerTodosOsDadosClienteUseCase.execute(id));
    }
}
