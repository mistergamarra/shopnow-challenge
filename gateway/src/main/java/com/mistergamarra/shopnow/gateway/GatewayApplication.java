package com.mistergamarra.shopnow.gateway;

import com.mistergamarra.shopnow.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	String accountSvcHost;
	String productSvcHost;
	String orderSvcHost;

	AuthenticationFilter filter;

	public GatewayApplication(@Value("${com.gateway.account-svc-address}") String accountSvcHost,
							  @Value("${com.gateway.product-svc-address}") String productSvcHost,
							  @Value("${com.gateway.order-svc-address}") String orderSvcHost,
							  AuthenticationFilter filter) {
		this.accountSvcHost = accountSvcHost;
		this.productSvcHost = productSvcHost;
		this.orderSvcHost = orderSvcHost;
		this.filter = filter;
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/api/orders/**")
						.filters(f ->
								f.addRequestHeader("x-auth-user", "a7c88cdd-e603-4efa-bca9-fd58901a25d6")
								.addRequestHeader("x-auth-user-role","admin")
								.filter(filter))
						.uri(orderSvcHost))
				.route(p -> p
						.path("/api/accounts/**")
						.filters(f -> f.addRequestHeader("x-auth-user", "a7c88cdd-e603-4efa-bca9-fd58901a25d6")
										.addRequestHeader("x-auth-user-role","admin")
										.filter(filter)
						)
						.uri(accountSvcHost))
				.route(p -> p
						.path("/api/products/**")
						.filters(f -> f.addRequestHeader("x-auth-user", "a7c88cdd-e603-4efa-bca9-fd58901a25d6")
								.addRequestHeader("x-auth-user-role","admin")
								.filter(filter)
						).uri(productSvcHost))
				.build();
	}
}
