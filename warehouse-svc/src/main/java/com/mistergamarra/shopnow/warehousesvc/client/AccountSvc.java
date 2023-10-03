package com.mistergamarra.shopnow.warehousesvc.client;


import com.mistergamarra.shopnow.warehousesvc.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-svc", url = "${com.shopnow.account-svc.address}", path = "/api/accounts")
public interface AccountSvc {

    @GetMapping("/{uuid}")
    AccountDto getAccountByUuid(@PathVariable String uuid);

}
