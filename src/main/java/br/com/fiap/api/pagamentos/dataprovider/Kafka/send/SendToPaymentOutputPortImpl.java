package br.com.fiap.api.pagamentos.dataprovider.Kafka.send;


import br.com.fiap.api.pagamentos.dataprovider.Kafka.OrderEvent;
import br.com.fiap.api.pagamentos.dataprovider.Kafka.message.PaymentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class SendToPaymentOutputPortImpl implements SendToPaymentOutputPort {

    @Autowired
    private KafkaTemplate<String, PaymentMessage> kafkaPayment;


    @Override
    public void send(PaymentMessage payment, OrderEvent event) {
        payment.setOrderEvent(OrderEvent.SUCCESS_PAYMENT);
        kafkaPayment.send("tp-saga-payment", payment);
    }


}