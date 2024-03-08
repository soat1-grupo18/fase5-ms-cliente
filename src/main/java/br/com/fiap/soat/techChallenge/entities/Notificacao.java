package br.com.fiap.soat.techChallenge.entities;

import java.util.UUID;

public record Notificacao(String idempotencyKey, UUID idCliente, String mensagem) { }