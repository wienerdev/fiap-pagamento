package br.com.fiap.api.pagamentos.dataprovider.Kafka;

import br.com.fiap.api.pagamentos.dataprovider.Kafka.message.PaymentMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;
import static org.apache.kafka.common.config.SaslConfigs.SASL_JAAS_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM;

@EnableKafka
@Configuration
public class KafkaSaleProducerConfig {

    // Confluent Cloud credentials
    @Value("${spring.kafka.bootstrap-servers}")
    private String CONFLUENT_CLOUD_BOOTSTRAP_SERVERS_VALUE;

    @Value("${confluent.cloud.api.key}")
    private String CONFLUENT_CLOUD_API_KEY;

    @Value("${confluent.cloud.api.secret}")
    private String CONFLUENT_CLOUD_API_SECRET;

    @Bean
    public ProducerFactory<String, PaymentMessage> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(BOOTSTRAP_SERVERS_CONFIG, CONFLUENT_CLOUD_BOOTSTRAP_SERVERS_VALUE);
        configProps.put(SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""+CONFLUENT_CLOUD_API_KEY+"\" password=\""+CONFLUENT_CLOUD_API_SECRET+"\";");
        configProps.put(SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        configProps.put(SASL_MECHANISM, "PLAIN");
        configProps.put(GROUP_ID_CONFIG, "payment");
        configProps.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, PaymentMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}


