package br.com.fiap.api.pagamentos.core.usecase;


import br.com.fiap.api.pagamentos.core.Payment;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.request.MakePaymentRequest;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.response.PaymentResponse;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.response.PaymentStatusResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentUseCase {

    List<Payment> getAll();

    PaymentResponse getById(UUID id);

    Payment makePayment(MakePaymentRequest request) throws Exception;

    PaymentStatusResponse getPaymentStatusByOrderId(UUID id);
}