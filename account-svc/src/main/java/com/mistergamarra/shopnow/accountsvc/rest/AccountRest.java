package com.mistergamarra.shopnow.accountsvc.rest;

import com.mistergamarra.shopnow.accountsvc.dto.AccountDto;
import com.mistergamarra.shopnow.accountsvc.dto.JwtDto;
import com.mistergamarra.shopnow.accountsvc.dto.LoginDto;
import com.mistergamarra.shopnow.accountsvc.service.AccountService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class AccountRest {
    AccountService productService;

    public AccountRest(AccountService productService) {
        this.productService = productService;
    }

    @Cacheable(value = "account", key = "#uuid")
    @GetMapping("/{uuid}")
    public AccountDto getAccountById(@PathVariable String uuid){
        return productService.getByUuid(uuid);
    }

    @CacheEvict(value = "account", allEntries = true)
    @PostMapping
    public AccountDto addAccount(@RequestBody AccountDto accountDto){
        return productService.save(accountDto);
    }

    @PostMapping("/login")
    public JwtDto login(@RequestBody LoginDto loginDto){
        return productService.login(loginDto);
    }
}
