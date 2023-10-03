package com.mistergamarra.shopnow.accountsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AccountSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountSvcApplication.class, args);
	}

}
