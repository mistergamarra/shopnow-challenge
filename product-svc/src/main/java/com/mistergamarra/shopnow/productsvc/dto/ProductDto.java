package com.mistergamarra.shopnow.productsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash(value="Product")
@Getter
@Setter
@ToString
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String uuid;
    private String name;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
