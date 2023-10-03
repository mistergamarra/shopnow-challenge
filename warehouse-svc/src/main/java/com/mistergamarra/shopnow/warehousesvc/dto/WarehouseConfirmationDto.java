package com.mistergamarra.shopnow.warehousesvc.dto;

import lombok.Data;

@Data
public class WarehouseConfirmationDto {
    public WarehouseConfirmationDto(String code) {
        this.code = code;
    }

    private String code;
}
