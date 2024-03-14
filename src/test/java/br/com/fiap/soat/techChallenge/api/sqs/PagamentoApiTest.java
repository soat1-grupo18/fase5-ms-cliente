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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PagamentoApiTest {
    PagamentoApi notificacaoApi = null;
    @Mock
    private NotificacaoGatewayPort notificacaoGatewayPort;

    @BeforeEach
    void setUp() {
        notificacaoApi = new PagamentoApi(new ObjectMapper(), notificacaoGatewayPort);
    }

    @Test
    public void deveDispararUmaExcecaoQuandoRecebeUmaMensagemInvalida() {
        assertThrows(JsonProcessingException.class, () -> {
            notificacaoApi.receberEventoDePagamentoAprovado("321");
        });

        assertThrows(JsonProcessingException.class, () -> {
            notificacaoApi.receberEventoDePagamentoRecusado("321");
        });
    }

    @Test
    public void naoDeveFazerNadaQuandoReceberUmPedidoAprovadoSemCliente() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": null, \"total\": 102.97, \"status\": \"APROVADO\" }";

        notificacaoApi.receberEventoDePagamentoAprovado(mensagem);

        verify(notificacaoGatewayPort, times(0)).notificarCliente(any());
    }

    @Test
    public void naoDeveFazerNadaQuandoReceberUmPedidoRecujadoSemCliente() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": null, \"total\": 102.97, \"status\": \"APROVADO\" }";

        notificacaoApi.receberEventoDePagamentoRecusado(mensagem);

        verify(notificacaoGatewayPort, times(0)).notificarCliente(any());
    }

    @Test
    public void naoDeveFazerNadaQuandoReceberUmPedidoAprovadoComStatusPendente() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": \"7db7eebe-3395-4bb2-a151-fb40085495f7\", \"total\": 102.97, \"status\": \"PENDENTE\" }";

        notificacaoApi.receberEventoDePagamentoAprovado(mensagem);

        verify(notificacaoGatewayPort, times(0)).notificarCliente(any());
    }

    @Test
    public void naoDeveFazerNadaQuandoReceberUmPedidoRecusadoComStatusPendente() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": \"7db7eebe-3395-4bb2-a151-fb40085495f7\", \"total\": 102.97, \"status\": \"PENDENTE\" }";

        notificacaoApi.receberEventoDePagamentoRecusado(mensagem);

        verify(notificacaoGatewayPort, times(0)).notificarCliente(any());
    }

    @Test
    public void deveEstourarUmaExececaoReceberUmPedidoAprovadoComStatusDesconhecido() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": \"7db7eebe-3395-4bb2-a151-fb40085495f7\", \"total\": 102.97, \"status\": \"???\" }";

        var ex = assertThrows(RuntimeException.class, () -> {
            notificacaoApi.receberEventoDePagamentoAprovado(mensagem);
        });

        verify(notificacaoGatewayPort, times(0)).notificarCliente(any());

        assertEquals("Mensagem com dados inesperados.", ex.getMessage());
    }

    @Test
    public void deveEstourarUmaExececaoReceberUmPedidoRecusadoComStatusDesconhecido() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": \"7db7eebe-3395-4bb2-a151-fb40085495f7\", \"total\": 102.97, \"status\": \"???\" }";

        var ex = assertThrows(RuntimeException.class, () -> {
            notificacaoApi.receberEventoDePagamentoRecusado(mensagem);
        });

        verify(notificacaoGatewayPort, times(0)).notificarCliente(any());

        assertEquals("Mensagem com dados inesperados.", ex.getMessage());
    }

    @Test
    public void deveInvocarGatewayDeNotificaoQuandoReceberUmPedidoAprovadoValido() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": \"7db7eebe-3395-4bb2-a151-fb40085495f7\", \"total\": 102.97, \"status\": \"APROVADO\" }";

        var notificacaoEsperada = new Notificacao(
                "bc2ef7f0-b1d5-4316-9981-5d60b16dfb86@APROVADO",
                UUID.fromString("7db7eebe-3395-4bb2-a151-fb40085495f7"),
                "O pagamento do pedido bc2ef7f0-b1d5-4316-9981-5d60b16dfb86 foi aprovado."
        );

        notificacaoApi.receberEventoDePagamentoAprovado(mensagem);

        verify(notificacaoGatewayPort).notificarCliente(notificacaoEsperada);
    }

    @Test
    public void deveInvocarGatewayDeNotificaoQuandoReceberUmPedidoRecusadoValido() throws JsonProcessingException {
        var mensagem = "{ \"pagamentoId\": \"ef95f91f-bf85-48b9-9e59-5b61105ab56f\", \"pedidoId\": \"bc2ef7f0-b1d5-4316-9981-5d60b16dfb86\", \"clienteId\": \"7db7eebe-3395-4bb2-a151-fb40085495f7\", \"total\": 102.97, \"status\": \"RECUSADO\" }";

        var notificacaoEsperada = new Notificacao(
                "bc2ef7f0-b1d5-4316-9981-5d60b16dfb86@RECUSADO",
                UUID.fromString("7db7eebe-3395-4bb2-a151-fb40085495f7"),
                "O pagamento do pedido bc2ef7f0-b1d5-4316-9981-5d60b16dfb86 foi recusado."
        );

        notificacaoApi.receberEventoDePagamentoRecusado(mensagem);

        verify(notificacaoGatewayPort).notificarCliente(notificacaoEsperada);
    }
}