package com.mistergamarra.shopnow.notificationsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NotificationSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationSvcApplication.class, args);
	}

}
