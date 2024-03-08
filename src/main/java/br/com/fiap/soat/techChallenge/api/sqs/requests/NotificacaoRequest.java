package br.com.fiap.soat.techChallenge.api.sqs.requests;

import br.com.fiap.soat.techChallenge.entities.Notificacao;

import java.util.UUID;

public class NotificacaoRequest {
    private String idempotencyKey;
    private UUID idCliente;
    private String mensagem;

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Notificacao toEntity() {
        return new Notificacao(idempotencyKey, idCliente, mensagem);
    }
}
