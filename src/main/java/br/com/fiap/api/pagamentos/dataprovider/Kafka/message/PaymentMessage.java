package br.com.fiap.api.pagamentos.dataprovider.Kafka.message;



import br.com.fiap.api.pagamentos.dataprovider.Kafka.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMessage {

    private UUID orderId;
    private String paymentMethod;
    private OrderEvent orderEvent;
}