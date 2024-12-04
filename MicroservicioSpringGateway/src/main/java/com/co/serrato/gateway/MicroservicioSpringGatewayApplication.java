package com.co.serrato.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioSpringGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSpringGatewayApplication.class, args);
	}

}
