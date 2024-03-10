package br.com.fiap.soat.techChallenge.controllers;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.exceptions.ClienteNaoEncontradoException;
import br.com.fiap.soat.techChallenge.interfaces.usecases.CadastrarClienteUseCasePort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.IdentificarClienteUseCasePort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.RemoverClienteUseCasePort;
import br.com.fiap.soat.techChallenge.presenters.ClientePresenter;

import java.util.UUID;

import org.springframework.stereotype.Controller;

@Controller
public class ClienteController {

    private final CadastrarClienteUseCasePort cadastrarClienteUseCase;
    private final IdentificarClienteUseCasePort identificarClienteUseCase;
    private final RemoverClienteUseCasePort removerClienteUseCase;

    public ClienteController(CadastrarClienteUseCasePort cadastrarClienteUseCase,
                             IdentificarClienteUseCasePort identificarClienteUseCase,
                             RemoverClienteUseCasePort removerClienteUseCase) {

        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.identificarClienteUseCase = identificarClienteUseCase;
        this.removerClienteUseCase = removerClienteUseCase;
    }

    public ClientePresenter identificarCliente(String cpf) {
        Cliente cliente = identificarClienteUseCase.execute(cpf).orElseThrow(() -> ClienteNaoEncontradoException.aPartirDoCpf(cpf));
        return ClientePresenter.fromDomain(cliente);
    }

    public ClientePresenter cadastrarCliente(Cliente cliente) {
        return ClientePresenter.fromDomain(cadastrarClienteUseCase.execute(cliente));
    }

    public void removerCliente(UUID id) {
        removerClienteUseCase.execute(id);
    }
}
