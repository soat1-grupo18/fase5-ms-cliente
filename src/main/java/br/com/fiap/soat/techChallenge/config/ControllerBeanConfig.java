package br.com.fiap.soat.techChallenge.config;

import br.com.fiap.soat.techChallenge.controllers.ClienteController;
import br.com.fiap.soat.techChallenge.interfaces.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeanConfig {

    @Bean
    public ClienteController clienteController(CadastrarClienteUseCasePort cadastrarClienteUseCase,
                                               IdentificarClienteUseCasePort identificarClienteUseCase) {
        return new ClienteController(cadastrarClienteUseCase, identificarClienteUseCase);
    }
}
