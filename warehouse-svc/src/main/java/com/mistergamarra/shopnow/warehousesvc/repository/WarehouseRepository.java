package com.mistergamarra.shopnow.warehousesvc.repository;

import com.mistergamarra.shopnow.warehousesvc.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    Optional<Warehouse> findByProductUuid(String productUuid);

}
