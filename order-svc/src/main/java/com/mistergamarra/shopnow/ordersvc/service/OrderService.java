package com.mistergamarra.shopnow.ordersvc.service;

import com.mistergamarra.shopnow.ordersvc.dto.OrderDto;
import com.mistergamarra.shopnow.ordersvc.model.Order;
import com.mistergamarra.shopnow.ordersvc.queue.OrderTopicProducer;
import com.mistergamarra.shopnow.ordersvc.repository.OrderRepository;
import com.mistergamarra.shopnow.ordersvc.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    public static final String ACCOUNT_WAS_FOUND_UUID_ID_TMPL = "msg=order was found uuid={} code={}";
    public static final String ACCOUNT_NOT_FOUND_MSG = "account not found";
    OrderRepository orderRepository;
    DozerBeanMapper dozerBeanMapper;


    @Value("com.account-svc.default-password")
    String defaultPassword;

    OrderTopicProducer topicProducer;

    public OrderService(OrderRepository orderRepository, DozerBeanMapper dozerBeanMapper, OrderTopicProducer topicProducer) {
        this.orderRepository = orderRepository;
        this.dozerBeanMapper = dozerBeanMapper;
        this.topicProducer = topicProducer;
    }

    public OrderDto getById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order p = order.get();
            log.info(ACCOUNT_WAS_FOUND_UUID_ID_TMPL,p.getUuid(),p.getCode());
            return dozerBeanMapper.map(p, OrderDto.class);
        }
        throw new NotFoundException(ACCOUNT_NOT_FOUND_MSG);
    }

    public OrderDto getByUuid(String uuid){
        Optional<Order> account = orderRepository.findAccountByUuid(uuid);
        if (account.isPresent()){
            Order p = account.get();
            log.info(ACCOUNT_WAS_FOUND_UUID_ID_TMPL,p.getUuid(),p.getCode());
            return dozerBeanMapper.map(p, OrderDto.class);
        }
        throw new NotFoundException(ACCOUNT_NOT_FOUND_MSG);
    }

    public OrderDto getByCode(String code){
        Optional<Order> order = orderRepository.findAccountByCode(code);
        if (order.isPresent()){
            Order p = order.get();
            log.info(ACCOUNT_WAS_FOUND_UUID_ID_TMPL,p.getUuid(),p.getCode());
            return dozerBeanMapper.map(p, OrderDto.class);
        }
        throw new NotFoundException(ACCOUNT_NOT_FOUND_MSG);
    }

    public OrderDto save(OrderDto orderDto){
        log.info("msg=creating order code={}", orderDto.getCode());
        Order order = dozerBeanMapper.map(orderDto, Order.class);
        order.setUuid(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        order.setModifiedAt(LocalDateTime.now(ZoneOffset.UTC));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        order.setCreatedBy(currentPrincipalName);
        order.setModifiedBy(currentPrincipalName);
        Order saved = orderRepository.save(order);
        OrderDto response = dozerBeanMapper.map(saved, OrderDto.class);
        topicProducer.send(response);
        return response;
    }


}
