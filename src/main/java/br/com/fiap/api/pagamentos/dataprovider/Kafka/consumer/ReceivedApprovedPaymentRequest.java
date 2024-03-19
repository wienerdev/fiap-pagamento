package br.com.fiap.api.pagamentos.dataprovider.Kafka.consumer;


import br.com.fiap.api.pagamentos.core.Payment;
import br.com.fiap.api.pagamentos.core.usecase.PaymentUseCase;
import br.com.fiap.api.pagamentos.dataprovider.Kafka.OrderEvent;
import br.com.fiap.api.pagamentos.dataprovider.Kafka.message.PaymentMessage;
import br.com.fiap.api.pagamentos.dataprovider.Kafka.send.SendToPaymentOutputPortImpl;
import br.com.fiap.api.pagamentos.entrypoint.controller.dto.request.MakePaymentRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class ReceivedApprovedPaymentRequest {

    private final PaymentUseCase paymentUseCase;

    private final SendToPaymentOutputPortImpl sendPayment;

    public ReceivedApprovedPaymentRequest(PaymentUseCase paymentUseCase, SendToPaymentOutputPortImpl sendPayment) {
        this.paymentUseCase = paymentUseCase;
        this.sendPayment = sendPayment;
    }

    @KafkaListener(topics = "tp-saga-payment", groupId = "payment")
    public void receive(PaymentMessage paymentMessage) throws Exception {
        if (OrderEvent.PROCESSING_PAYMENT.equals(paymentMessage.getOrderEvent() )) {
            var teste = new MakePaymentRequest(paymentMessage.getOrderId(), paymentMessage.getPaymentMethod());
            Payment pay = paymentUseCase.makePayment(teste);
            sendPayment.send(new PaymentMessage(pay.getOrder(),"Débito",OrderEvent.SUCCESS_PAYMENT), OrderEvent.SUCCESS_PAYMENT);
            System.out.println("Pagamento Realizado com sucesso.-------------------------------------------------------------");

        }

    }
}


