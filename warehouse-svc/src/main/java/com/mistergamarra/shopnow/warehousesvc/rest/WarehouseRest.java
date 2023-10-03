package com.mistergamarra.shopnow.warehousesvc.rest;

import com.mistergamarra.shopnow.warehousesvc.dto.OrderDto;
import com.mistergamarra.shopnow.warehousesvc.dto.UpdateStockDto;
import com.mistergamarra.shopnow.warehousesvc.dto.WarehouseDto;
import com.mistergamarra.shopnow.warehousesvc.service.WarehouseService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
public class WarehouseRest {
    WarehouseService warehouseService;

    public WarehouseRest(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Cacheable(value="warehouse-key", key = "#root.methodName")
    @GetMapping
     public List<WarehouseDto> getProducts(){
         return warehouseService.findAll();
     }

    @Cacheable(value = "warehouse-key", key = "#productUuid")
    @GetMapping("/{productUuid}")
    public WarehouseDto getProductByUuid(@PathVariable String productUuid){
        return warehouseService.getByProductUuid(productUuid);
    }

    @CacheEvict(value = "warehouse-key", allEntries = true)
    @PostMapping
    public void updateStock(@RequestBody OrderDto orderDto) throws InterruptedException {
        warehouseService.processOrder(orderDto);
    }
}
