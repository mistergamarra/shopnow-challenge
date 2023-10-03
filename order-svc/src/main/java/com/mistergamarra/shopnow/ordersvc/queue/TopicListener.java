package com.mistergamarra.shopnow.ordersvc.queue;

import com.mistergamarra.shopnow.ordersvc.dto.WarehouseConfirmationDto;
import com.mistergamarra.shopnow.ordersvc.service.OrderConfirmationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TopicListener {

    private final String topicName;

   OrderConfirmationService orderConfirmationService;

    public TopicListener(@Value("${topic.name.consumer}") String topicName, OrderConfirmationService orderConfirmationService) {
        this.topicName = topicName;
        this.orderConfirmationService = orderConfirmationService;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, WarehouseConfirmationDto> payload) {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        orderConfirmationService.notifyByEmail(payload.value());
    }

}