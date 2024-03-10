package br.com.fiap.api.pagamentos.application.gateway;

import br.com.fiap.api.pagamentos.core.usecase.MercadoPagoWebhookUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MercadoPagoWebhookGateway {

    private final MercadoPagoWebhookUseCase mercadoPagoWebhookUseCase;

    public MercadoPagoWebhookGateway(MercadoPagoWebhookUseCase mercadoPagoWebhookUseCase) {
        this.mercadoPagoWebhookUseCase = mercadoPagoWebhookUseCase;
    }

    public ResponseEntity<String> handleMercadoPagoNotification(Map<String, Object> payload) {
        return new ResponseEntity<>(
                mercadoPagoWebhookUseCase.handleMercadoPagoNotification(payload), HttpStatus.OK);
    }
}