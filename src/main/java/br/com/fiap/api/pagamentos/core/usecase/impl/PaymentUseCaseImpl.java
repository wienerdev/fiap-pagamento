package br.com.fiap.api.pagamentos.core.usecase.impl;

import br.com.fiap.api.pagamentos.core.Payment;
import br.com.fiap.api.pagamentos.core.dataprovider.repository.PaymentRepository;
import br.com.fiap.api.pagamentos.core.usecase.PaymentUseCase;
import br.com.fiap.api.pagamentos.dataprovider.enumeration.OrderStatusEnum;
import br.com.fiap.api.pagamentos.entrypoint.client.OrderClient;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.request.MakePaymentRequest;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.response.PaymentResponse;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.response.PaymentStatusResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentRepository paymentRepositoryPort;

    private final OrderClient orderClient;

    public PaymentUseCaseImpl(PaymentRepository paymentRepository, OrderClient orderClient) {
        this.paymentRepositoryPort = paymentRepository;
        this.orderClient = orderClient;
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepositoryPort.getAll();
    }

    @Override
    public PaymentResponse getById(UUID id) {
        Payment payment = paymentRepositoryPort.getById(id);
        return new PaymentResponse(payment.getPaymentId(), payment.getOrder(), payment.getPaymentMethod());
    }

    @Override
    public Payment makePayment(MakePaymentRequest request) throws Exception {

        boolean paymentResponse = mercadoPagoIntegrationOrderPay();

        if (paymentResponse) {
            orderClient.update(new UpdateOrderRequest(request.orderId(), OrderStatusEnum.PAID));
            return paymentRepositoryPort.makePayment(new Payment(UUID.randomUUID(), request.orderId(), request.paymentMethod()));
        }

        throw new Exception("There was a problem with the payment, please try again!");
    }

    @Override
    public PaymentStatusResponse getPaymentStatusByOrderId(UUID orderId) {
        if (Boolean.TRUE.equals(orderClient.getPaymentReceivedById(orderId).getBody())) {
            return new PaymentStatusResponse(true);
        }

        return new PaymentStatusResponse(false);
    }

    private boolean mercadoPagoIntegrationOrderPay() {
        return true;
    }

}