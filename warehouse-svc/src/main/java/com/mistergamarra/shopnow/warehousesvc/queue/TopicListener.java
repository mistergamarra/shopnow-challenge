package com.mistergamarra.shopnow.warehousesvc.queue;

import com.mistergamarra.shopnow.warehousesvc.dto.OrderDto;
import com.mistergamarra.shopnow.warehousesvc.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TopicListener {

    private String topicName;

    WarehouseService warehouseService;

    public TopicListener(@Value("${topic.name.consumer}") String topicName, WarehouseService warehouseService) {
        this.topicName = topicName;
        this.warehouseService = warehouseService;
    }

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, OrderDto> payload) {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        OrderDto order = payload.value();
        warehouseService.processOrder(order);

    }

}