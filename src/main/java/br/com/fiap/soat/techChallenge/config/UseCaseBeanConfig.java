package br.com.fiap.soat.techChallenge.config;

import br.com.fiap.soat.techChallenge.interfaces.gateways.ClienteGatewayPort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.CadastrarClienteUseCasePort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.IdentificarClienteUseCasePort;
import br.com.fiap.soat.techChallenge.interfaces.usecases.RemoverTodosOsDadosClienteUseCasePort;
import br.com.fiap.soat.techChallenge.usecases.CadastrarClienteUseCase;
import br.com.fiap.soat.techChallenge.usecases.IdentificarClienteUseCase;
import br.com.fiap.soat.techChallenge.usecases.RemoverTodosOsDadosClienteUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    public CadastrarClienteUseCasePort cadastrarClienteUseCasePort(ClienteGatewayPort clienteGatewayPort) {
        return new CadastrarClienteUseCase(clienteGatewayPort);
    }

    @Bean
    public IdentificarClienteUseCasePort identificarClienteUseCasePort(ClienteGatewayPort clienteGatewayPort) {
        return new IdentificarClienteUseCase(clienteGatewayPort);
    }

    @Bean
    public RemoverTodosOsDadosClienteUseCasePort removerTodosOsDadosClienteUseCasePort(ClienteGatewayPort clienteGatewayPort) {
        return new RemoverTodosOsDadosClienteUseCase(clienteGatewayPort);
    }
}
