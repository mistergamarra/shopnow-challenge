package com.mistergamarra.shopnow.ordersvc.rest;

import com.mistergamarra.shopnow.ordersvc.dto.OrderDto;
import com.mistergamarra.shopnow.ordersvc.service.OrderService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderRest {
    OrderService orderService;

    public OrderRest(OrderService orderService) {
        this.orderService = orderService;
    }

    /*@Cacheable(value = "order", key = "#uuid")
    @GetMapping("/{uuid}")
    public OrderDto getOrderByUuid(@PathVariable String uuid){
        return orderService.getByUuid(uuid);
    }*/

   /* @Cacheable(value = "order", key = "#id")
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }
*/

    @Cacheable(value = "order", key = "#code")
    @GetMapping("/{code}")
    public OrderDto getOrderByCode(@PathVariable String code){
        return orderService.getByCode(code);
    }

    @CacheEvict(value = "order", allEntries = true)
    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto product){
        return orderService.save(product);
    }
}
