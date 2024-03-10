package br.com.fiap.api.pagamentos.core;

import br.com.fiap.api.pagamentos.dataprovider.repository.entity.PaymentEntity;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.response.PaymentResponse;

import java.util.UUID;

public class Payment {

    private UUID paymentId;
    private UUID orderId;
    private String paymentMethod;

    public Payment() {
    }

    public Payment(UUID paymentId, UUID orderId, String paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getOrder() {
        return orderId;
    }

    public void setOrder(UUID order) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentEntity toEntity() {
        return new PaymentEntity(this.paymentId, this.orderId, this.paymentMethod);
    }

    public PaymentResponse toResponse() {
        return new PaymentResponse(this.paymentId, this.orderId, this.paymentMethod);
    }
}