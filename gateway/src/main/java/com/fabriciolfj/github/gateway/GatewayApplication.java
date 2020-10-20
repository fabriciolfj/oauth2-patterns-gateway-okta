package com.fabriciolfj.github.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(final RouteLocatorBuilder builder, final TokenRelayGatewayFilterFactory filterFactory) {
		return builder.routes()
				.route("cart", r -> r.path("/cart/**")
						.filters(f -> f.filter(filterFactory.apply()))
						.uri("lb://cart"))
				.build();
	}

}
