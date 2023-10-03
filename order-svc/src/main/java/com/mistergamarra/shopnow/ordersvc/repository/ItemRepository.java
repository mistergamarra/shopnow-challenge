package com.mistergamarra.shopnow.ordersvc.repository;

import com.mistergamarra.shopnow.ordersvc.model.Item;
import com.mistergamarra.shopnow.ordersvc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

}
