package br.com.fiap.soat.techChallenge.api.sqs;

import br.com.fiap.soat.techChallenge.api.sqs.requests.PagamentoEvent;
import br.com.fiap.soat.techChallenge.entities.Notificacao;
import br.com.fiap.soat.techChallenge.interfaces.gateways.NotificacaoGatewayPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PagamentoApi {
    private final Logger logger = LoggerFactory.getLogger(PagamentoApi.class);
    private final ObjectMapper mapper;
    private final NotificacaoGatewayPort notificacaoGatewayPort;

    public PagamentoApi(ObjectMapper mapper, NotificacaoGatewayPort notificacaoGatewayPort) {
        this.mapper = mapper;
        this.notificacaoGatewayPort = notificacaoGatewayPort;
    }

    @SqsListener("${sqs.queues.pagamento-aprovado}")
    public void receberEventoDePagamentoAprovado(String evento) throws JsonProcessingException {
        logger.info("Processando evento de pagamento aprovado: {}", evento);
        PagamentoEvent pagamentoAprovado = mapper.readValue(evento, PagamentoEvent.class);
        notificar(pagamentoAprovado);
    }

    @SqsListener("${sqs.queues.pagamento-recusado}")
    public void receberEventoDePagamentoRecusado(String evento) throws JsonProcessingException {
        logger.info("Processando evento de pagamento recusado: {}", evento);
        PagamentoEvent pagamentoRecusado = mapper.readValue(evento, PagamentoEvent.class);
        notificar(pagamentoRecusado);
    }

    private void notificar(PagamentoEvent pagamento) {
        Notificacao notificacaoEntity = pagamento.toEntity();
        if (notificacaoEntity != null) notificacaoGatewayPort.notificarCliente(notificacaoEntity);
    }
}
