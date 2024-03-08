package br.com.fiap.soat.techChallenge.api.sqs;

import br.com.fiap.soat.techChallenge.api.sqs.requests.NotificacaoRequest;
import br.com.fiap.soat.techChallenge.entities.Notificacao;
import br.com.fiap.soat.techChallenge.interfaces.gateways.NotificacaoGatewayPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoApi {
    private final Logger logger = LoggerFactory.getLogger(NotificacaoApi.class);
    private final ObjectMapper mapper;
    private final NotificacaoGatewayPort notificacaoGatewayPort;

    public NotificacaoApi(ObjectMapper mapper, NotificacaoGatewayPort notificacaoGatewayPort) {
        this.mapper = mapper;
        this.notificacaoGatewayPort = notificacaoGatewayPort;
    }

    @SqsListener("${sqs.queues.notificacao}")
    public void receberComandoDeNotificacao(String notificacaoAsString) throws JsonProcessingException {
        logger.info("Processando comando de notificação: {}", notificacaoAsString);
        NotificacaoRequest notificacaoRequest = mapper.readValue(notificacaoAsString, NotificacaoRequest.class);
        Notificacao notificacaoEntity = notificacaoRequest.toEntity();
        notificacaoGatewayPort.notificarCliente(notificacaoEntity);
    }
}
