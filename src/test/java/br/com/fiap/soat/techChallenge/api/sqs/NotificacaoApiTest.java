package br.com.fiap.soat.techChallenge.api.sqs;

import br.com.fiap.soat.techChallenge.entities.Notificacao;
import br.com.fiap.soat.techChallenge.interfaces.gateways.NotificacaoGatewayPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificacaoApiTest {
    NotificacaoApi notificacaoApi = null;
    @Mock
    private NotificacaoGatewayPort notificacaoGatewayPort;

    @BeforeEach
    void setUp() {
        notificacaoApi = new NotificacaoApi(new ObjectMapper(), notificacaoGatewayPort);
    }

    @Test
    public void deveDispararUmaExcecaoQuandoRecebeUmaMensagemInvalida() {
        assertThrows(JsonProcessingException.class, () -> {
            notificacaoApi.receberComandoDeNotificacao("321");
        });
    }

    @Test
    public void deveProcessarUmaMensagem() throws JsonProcessingException {
        var mensagem = "{ \"idempotencyKey\": \"59ebeeaa-0fa0-45dc-8752-9dd3effc35bd\", \"idCliente\": \"bcf7374b-6cdf-4e3a-842c-94ceade83c04\", \"mensagem\": \"Olá! (:\" }";
        var notificacaoEsperada = new Notificacao("59ebeeaa-0fa0-45dc-8752-9dd3effc35bd", UUID.fromString("bcf7374b-6cdf-4e3a-842c-94ceade83c04"), "Olá! (:");

        notificacaoApi.receberComandoDeNotificacao(mensagem);

        verify(notificacaoGatewayPort).notificarCliente(notificacaoEsperada);
    }
}