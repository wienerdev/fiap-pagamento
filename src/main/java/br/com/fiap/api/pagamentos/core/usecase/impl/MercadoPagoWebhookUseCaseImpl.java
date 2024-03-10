package br.com.fiap.api.pagamentos.core.usecase.impl;

import br.com.fiap.api.pagamentos.core.usecase.MercadoPagoWebhookUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MercadoPagoWebhookUseCaseImpl implements MercadoPagoWebhookUseCase {

    private static final Logger logger = LoggerFactory.getLogger(MercadoPagoWebhookUseCaseImpl.class);

    @Override
    public String handleMercadoPagoNotification(Map<String, Object> payload) {
        String action = (String) payload.get("action");

        if (action == null) {
            logger.warn("Webhook recebido sem ação");
            return "Payload inválido";
        }

        if (action.equalsIgnoreCase("test.created")) {
            return handleTestCreated(payload);
        }
        logger.warn("Ação desconhecida: {}", action);
        return "Ação desconhecida";
    }

    private String handleTestCreated(Map<String, Object> payload) {
        logger.info("Ação de teste criada: {}", payload);
        return String.format("Ação de teste criada: {}", payload);
    }

}