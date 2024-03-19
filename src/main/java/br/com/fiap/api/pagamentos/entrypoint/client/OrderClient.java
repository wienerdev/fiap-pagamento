package br.com.fiap.api.pagamentos.entrypoint.client;

import br.com.fiap.api.pagamentos.entrypoint.controller.dto.request.UpdateOrderRequest;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "orderClient", url = "http://localhost:8083/api/v1/orders")
public interface OrderClient {

    @GetMapping
    ResponseEntity<String> getAllOrders();

    @GetMapping("/payment-received/{id}")
    ResponseEntity<Boolean> getPaymentReceivedById(@PathVariable UUID id);

    @PutMapping("/update-status")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateOrderRequest request);

}