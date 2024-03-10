package br.com.fiap.api.pagamentos.entrypoint.controller.dto.response;

import br.com.fiap.api.pagamentos.core.Payment;

import java.util.UUID;

public record PaymentResponse(UUID paymentId, UUID orderId, String paymentMethod) {

    public static PaymentResponse fromEntityToResponse(Payment payment) {
        return new PaymentResponse(payment.getPaymentId(), payment.getOrder(), payment.getPaymentMethod());
    }
}