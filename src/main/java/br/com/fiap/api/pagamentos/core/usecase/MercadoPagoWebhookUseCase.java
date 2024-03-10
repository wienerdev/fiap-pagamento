package br.com.fiap.api.pagamentos.core.usecase;

import java.util.Map;

public interface MercadoPagoWebhookUseCase {

    String handleMercadoPagoNotification(Map<String, Object> payload);

}