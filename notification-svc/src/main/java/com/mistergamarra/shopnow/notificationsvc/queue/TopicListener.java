package com.mistergamarra.shopnow.notificationsvc.queue;

import com.mistergamarra.shopnow.notificationsvc.dto.OrderDto;
import com.mistergamarra.shopnow.notificationsvc.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TopicListener {

    private String topicName;

    NotificationService notificationService;

    public TopicListener(@Value("${topic.name.consumer}") String topicName, NotificationService notificationService) {
        this.topicName = topicName;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, OrderDto> payload){

        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
        notificationService.notifyByEmail(payload.value().getCode());

    }

}