package com.mistergamarra.shopnow.accountsvc.dto;

import com.mistergamarra.shopnow.accountsvc.enums.AccountType;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash(value="Product")
@Data
public class AccountDto implements Serializable {

    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String uuid;
    private String username;
    private String role;
    private String email;
    private AccountType accountType;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
