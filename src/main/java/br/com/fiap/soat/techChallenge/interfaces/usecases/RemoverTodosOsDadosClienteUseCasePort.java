package br.com.fiap.soat.techChallenge.interfaces.usecases;

import br.com.fiap.soat.techChallenge.entities.Cliente;

import java.util.UUID;

public interface RemoverTodosOsDadosClienteUseCasePort {
    void execute(UUID id);
}
