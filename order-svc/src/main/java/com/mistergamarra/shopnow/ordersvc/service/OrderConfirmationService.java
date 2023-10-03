package com.mistergamarra.shopnow.ordersvc.service;

import com.mistergamarra.shopnow.ordersvc.dto.OrderDto;
import com.mistergamarra.shopnow.ordersvc.dto.WarehouseConfirmationDto;
import com.mistergamarra.shopnow.ordersvc.model.Order;
import com.mistergamarra.shopnow.ordersvc.queue.OrderNotificationTopicProducer;
import com.mistergamarra.shopnow.ordersvc.repository.OrderRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderConfirmationService {

    OrderRepository orderRepository;

    DozerBeanMapper dozerBeanMapper;

    OrderNotificationTopicProducer topicProducer;

    public OrderConfirmationService(OrderRepository orderRepository, DozerBeanMapper dozerBeanMapper, OrderNotificationTopicProducer topicProducer) {
        this.orderRepository = orderRepository;
        this.dozerBeanMapper = dozerBeanMapper;
        this.topicProducer = topicProducer;
    }

    public void notifyByEmail(WarehouseConfirmationDto warehouseConfirmationDto){
        Optional<Order> order = orderRepository.findAccountByCode(warehouseConfirmationDto.getCode());
        if (order.isPresent()){
            Order order1 = order.get();
            OrderDto response = dozerBeanMapper.map(order1, OrderDto.class);
            topicProducer.send(response);
        }
    }

}
