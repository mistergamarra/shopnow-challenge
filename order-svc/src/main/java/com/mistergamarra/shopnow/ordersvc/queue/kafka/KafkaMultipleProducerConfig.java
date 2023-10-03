package com.mistergamarra.shopnow.ordersvc.queue.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaMultipleProducerConfig {

    private final KafkaCustomProperties kafkaCustomProperties;

    @Bean
    @Qualifier("orderProducer")
    public KafkaTemplate<String, Object> orderProducer() {
        return new KafkaTemplate<>(producerFactory("producer1"));
    }

    @Bean
    @Qualifier("orderNotificationProducer")
    public KafkaTemplate<String, Object> orderNotificationProducer() {
        return new KafkaTemplate<>(producerFactory("producer2"));
    }

    private ProducerFactory<String, Object> producerFactory(String producerName) {
        Map<String, Object> properties = new HashMap<>(kafkaCustomProperties.buildCommonProperties());
        if (nonNull(kafkaCustomProperties.getProducer())) {
            KafkaProperties.Producer producerProperties = kafkaCustomProperties.getProducer().get(producerName);
            if (nonNull(producerProperties)) {
                properties.putAll(producerProperties.buildProperties());
            }
        }
        log.info("Kafka Producer '{}' properties: {}", producerName, properties);
        return new DefaultKafkaProducerFactory<>(properties);
    }
}