package com.mistergamarra.shopnow.accountsvc.model;

import com.mistergamarra.shopnow.accountsvc.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Account {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String uuid;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private Long role;
    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column
    private String createdBy;
    @Column
    private String modifiedBy;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime modifiedAt;

}


