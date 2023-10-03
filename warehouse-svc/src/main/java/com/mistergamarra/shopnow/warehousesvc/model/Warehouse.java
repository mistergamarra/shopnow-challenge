package com.mistergamarra.shopnow.warehousesvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
public class Warehouse {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String productUuid;
    @Column
    private BigDecimal stock;
    @Column
    private String createdBy;
    @Column
    private String modifiedBy;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime modifiedAt;

}


