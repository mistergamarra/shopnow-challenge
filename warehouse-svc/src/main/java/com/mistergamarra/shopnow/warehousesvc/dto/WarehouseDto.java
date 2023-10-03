package com.mistergamarra.shopnow.warehousesvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class WarehouseDto implements Serializable {

    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String productUuid;
    private BigDecimal stock;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
