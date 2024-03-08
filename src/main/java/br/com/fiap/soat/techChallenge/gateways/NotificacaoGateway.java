package br.com.fiap.soat.techChallenge.gateways;

import br.com.fiap.soat.techChallenge.entities.Notificacao;
import br.com.fiap.soat.techChallenge.interfaces.gateways.NotificacaoGatewayPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoGateway implements NotificacaoGatewayPort {
    private final Logger logger = LoggerFactory.getLogger(NotificacaoGateway.class);

    @Override
    public void notificarCliente(Notificacao notificacao) {
        logger.info("Enviando notificação: {}", notificacao);
        // TODO: não é necessário enviar uma notificação real, mas isto poderia ser implementado aqui.
    }
}
