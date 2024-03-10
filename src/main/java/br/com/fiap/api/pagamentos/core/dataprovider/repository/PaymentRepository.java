package br.com.fiap.api.pagamentos.core.dataprovider.repository;

import br.com.fiap.api.pagamentos.core.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository {

    List<Payment> getAll();
    Payment getById(UUID id);
    Payment makePayment(Payment payment);
}