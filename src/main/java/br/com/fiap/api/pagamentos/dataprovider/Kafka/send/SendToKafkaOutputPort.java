package br.com.fiap.api.pagamentos.dataprovider.Kafka.send;


import br.com.fiap.api.pagamentos.dataprovider.Kafka.OrderEvent;
import br.com.fiap.api.pagamentos.dataprovider.Kafka.message.PaymentMessage;

public interface SendToKafkaOutputPort {

    void send(PaymentMessage order, OrderEvent event);

}
