package br.com.fiap.api.pagamentos.entrypoint.controller.dto.request;

import java.util.UUID;

public record MakePaymentRequest(UUID orderId, String paymentMethod) {
}