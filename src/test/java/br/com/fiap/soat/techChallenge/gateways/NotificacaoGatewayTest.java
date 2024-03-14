package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Notificacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class NotificacaoGatewayTest {
    NotificacaoGateway notificacaoGateway = null;

    @BeforeEach
    void setUp() {
        notificacaoGateway = new NotificacaoGateway();
    }

    @Test
    public void naoDeveFazerNadaPoisNaoEhNecessarioEnviarUmaNotificacaoDeVerdade() {
        var notificacao = new Notificacao("59ebeeaa-0fa0-45dc-8752-9dd3effc35bd", UUID.fromString("bcf7374b-6cdf-4e3a-842c-94ceade83c04"), "Olá! (:");
        notificacaoGateway.notificarCliente(notificacao);
    }
}