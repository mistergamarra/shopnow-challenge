package com.mistergamarra.shopnow.productsvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String uuid;
    @Column
    private String name;
    @Column
    private String createdBy;
    @Column
    private String modifiedBy;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime modifiedAt;

}


