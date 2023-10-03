package com.mistergamarra.shopnow.ordersvc.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderNotificationTopicProducer {

    @Value("${kafka.producer.producer2.topic}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderNotificationTopicProducer(@Qualifier("orderNotificationProducer") KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(Object message){
        log.info("Payload enviado: {}", message);
        kafkaTemplate.send(topicName, message);
    }

}