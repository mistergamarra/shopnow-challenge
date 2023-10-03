package com.mistergamarra.shopnow.ordersvc.repository;

import com.mistergamarra.shopnow.ordersvc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findAccountByUuid(String uuid);

    Optional<Order> findAccountByCode(String code);

}
