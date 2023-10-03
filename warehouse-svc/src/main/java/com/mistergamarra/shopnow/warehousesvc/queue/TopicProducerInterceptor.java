package com.mistergamarra.shopnow.warehousesvc.queue;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class TopicProducerInterceptor implements ProducerInterceptor<String, Object> {


    @Override
    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {

        /*String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        record.headers().add("x-auth-user",username.getBytes());*/
        return null;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // TODO document why this method is empty
    }

    @Override
    public void close() {
        // TODO document why this method is empty
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // TODO document why this method is empty
    }
}
