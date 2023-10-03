package com.mistergamarra.shopnow.warehousesvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@ToString

public class UpdateStockDto implements Serializable {

    public UpdateStockDto(Long id, String productUuid, String userUuid, BigDecimal quantity) {
        this.id = id;
        this.productUuid = productUuid;
        this.userUuid = userUuid;
        this.quantity = quantity;
    }

    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String productUuid;
    private String userUuid;
    private BigDecimal quantity;
}
