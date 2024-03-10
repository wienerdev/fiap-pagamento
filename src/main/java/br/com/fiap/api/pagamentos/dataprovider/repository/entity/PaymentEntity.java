package br.com.fiap.api.pagamentos.dataprovider.repository.entity;

import br.com.fiap.api.pagamentos.core.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    private UUID paymentId;

    private UUID orderId;

    private String paymentMethod;

    public PaymentEntity() {
    }

    public PaymentEntity(UUID paymentId, UUID orderId, String paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
    }

    public PaymentEntity(Payment payment) {
        this.paymentId = payment.getPaymentId();
        this.orderId = payment.getOrder();
        this.paymentMethod = payment.getPaymentMethod();
    }

    public Payment toPayment() {
        return new Payment(
                this.paymentId,
                this.orderId,
                this.paymentMethod);
    }
}