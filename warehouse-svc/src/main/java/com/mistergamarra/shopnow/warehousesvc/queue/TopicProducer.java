package com.mistergamarra.shopnow.warehousesvc.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public org.apache.kafka.clients.producer.ProducerInterceptor<String, Object> myProducerInterceptor() {
        return new TopicProducerInterceptor();
    }

    public void send(Object message){
        log.info("Payload enviado: {}", message);
        kafkaTemplate.setProducerInterceptor(myProducerInterceptor());
        kafkaTemplate.send(topicName, message);
    }

}