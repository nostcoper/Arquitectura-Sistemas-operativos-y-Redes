package com.co.serrato.microservicio.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroServicioEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicioEurekaApplication.class, args);
	}

}
