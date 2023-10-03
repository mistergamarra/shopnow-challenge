package com.mistergamarra.shopnow.warehousesvc.service;

import com.mistergamarra.shopnow.warehousesvc.client.AccountSvc;
import com.mistergamarra.shopnow.warehousesvc.dto.*;
import com.mistergamarra.shopnow.warehousesvc.enums.AccountType;
import com.mistergamarra.shopnow.warehousesvc.exception.BusinessException;
import com.mistergamarra.shopnow.warehousesvc.exception.NotFoundException;
import com.mistergamarra.shopnow.warehousesvc.model.Warehouse;
import com.mistergamarra.shopnow.warehousesvc.queue.TopicProducer;
import com.mistergamarra.shopnow.warehousesvc.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class WarehouseService {

    WarehouseRepository warehouseRepository;
    DozerBeanMapper dozerBeanMapper;

    AccountSvc accountService;

    TopicProducer topicProducer;


    public WarehouseService(WarehouseRepository warehouseRepository, DozerBeanMapper dozerBeanMapper, AccountSvc accountService, TopicProducer topicProducer) {
        this.warehouseRepository = warehouseRepository;
        this.dozerBeanMapper = dozerBeanMapper;
        this.accountService = accountService;
        this.topicProducer = topicProducer;
    }

    public List<WarehouseDto> findAll(){
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        List<WarehouseDto> warehouseDtoList = new ArrayList<>();
        warehouseList.forEach(p->{
            WarehouseDto warehouseDto = dozerBeanMapper.map(p, WarehouseDto.class);
            warehouseDtoList.add(warehouseDto);
        });
        return warehouseDtoList;
    }

    public WarehouseDto getByProductUuid(String productUuid){
        Optional<Warehouse> warehouse = warehouseRepository.findByProductUuid(productUuid);
        if (warehouse.isPresent()){
            Warehouse p = warehouse.get();
            return dozerBeanMapper.map(p, WarehouseDto.class);
        }
        throw new NotFoundException("warehouse not found");
    }

    @Transactional
    public void processOrder(OrderDto orderDto) {

        String user=orderDto.getUserUuid();
        AccountDto account = accountService.getAccountByUuid(user);

        for (ItemDto item:orderDto.getItems()) {
            Optional<Warehouse> warehouseOpt = warehouseRepository.findByProductUuid(item.getProductUuid());
            if (warehouseOpt.isEmpty()){
                throw new BusinessException("Item not found");
            }
            Warehouse warehouse = warehouseOpt.get();

            BigDecimal currentStock = warehouse.getStock();
            if (currentStock.compareTo(BigDecimal.valueOf(1.0)) < 0){
                throw new BusinessException("stock can't be less than zero");
            }
            BigDecimal newStock = currentStock.subtract(item.getQuantity());
            // Apply bonus
            if (account.getAccountType() == AccountType.PREMIUM){
                newStock = newStock.subtract(BigDecimal.valueOf(1.0));
            }
            warehouse.setStock(newStock);
            warehouseRepository.save(warehouse);
        }
        topicProducer.send(new WarehouseConfirmationDto(orderDto.getCode()));
    }


}
