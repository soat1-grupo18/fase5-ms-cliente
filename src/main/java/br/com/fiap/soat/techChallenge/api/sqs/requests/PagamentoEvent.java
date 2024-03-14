package br.com.fiap.soat.techChallenge.api.sqs.requests;

import br.com.fiap.soat.techChallenge.entities.Notificacao;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class PagamentoEvent {
    private String pagamentoId;
    private String pedidoId;
    private String clienteId;
    private BigDecimal total;
    private String status;

    public Notificacao toEntity() {
        if (clienteId == null || clienteId == "") return null;

        if (Objects.equals(status, "PENDENTE")) return null;

        if (Objects.equals(status, "APROVADO")) return new Notificacao(
                String.format("%s@%s", pedidoId, status),
                UUID.fromString(clienteId),
                String.format("O pagamento do pedido %s foi aprovado.", pedidoId)
        );

        if (Objects.equals(status, "RECUSADO")) return new Notificacao(
                String.format("%s@%s", pedidoId, status),
                UUID.fromString(clienteId),
                String.format("O pagamento do pedido %s foi recusado.", pedidoId)
        );

        throw new RuntimeException("Mensagem com dados inesperados.");
    }

    public String getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(String pagamentoId) {
        this.pagamentoId = pagamentoId;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
