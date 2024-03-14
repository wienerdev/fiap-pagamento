package br.com.fiap.api.pagamentos.dataprovider.Kafka;

import br.com.fiap.api.pagamentos.dataprovider.Kafka.message.PaymentMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class CustomSerializer implements Serializer<PaymentMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, PaymentMessage orderMessage) {
        try {
            if (orderMessage == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(orderMessage);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing SaleMessage to byte[]");
        }
    }

}
