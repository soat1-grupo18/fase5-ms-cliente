package br.com.fiap.soat.techChallenge.interfaces.gateways;

import br.com.fiap.soat.techChallenge.entities.Notificacao;

public interface NotificacaoGatewayPort {
    void notificarCliente(Notificacao notificacao);
}
