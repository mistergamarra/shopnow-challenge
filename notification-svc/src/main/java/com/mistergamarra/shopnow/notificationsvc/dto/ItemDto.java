package com.mistergamarra.shopnow.notificationsvc.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ItemDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String productUuid;
    private BigDecimal quantity;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
