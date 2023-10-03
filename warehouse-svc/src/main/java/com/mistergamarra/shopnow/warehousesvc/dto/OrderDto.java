package com.mistergamarra.shopnow.warehousesvc.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String uuid;
    private String code;
    private String userUuid;
    private List<ItemDto> items;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
