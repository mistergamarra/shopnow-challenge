package com.mistergamarra.shopnow.warehousesvc.dto;

import com.mistergamarra.shopnow.warehousesvc.enums.AccountType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountDto implements Serializable {

    private static final long serialVersionUID = 6045497972690240600L;

    private Long id;
    private String uuid;
    private String username;
    private String role;
    private AccountType accountType;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
