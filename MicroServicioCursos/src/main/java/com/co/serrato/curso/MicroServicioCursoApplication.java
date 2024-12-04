package com.co.serrato.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.co.serrato.common.usuario.entity", "com.co.serrato.curso.models.entity", "com.co.serrato"})
@EnableJpaRepositories({"com.co.serrato.common.usuario.repository", "com.co.serrato.curso.repository"})
@EntityScan({"com.co.serrato.common.usuario.entity", "com.co.serrato.curso.models.entity"})
public class MicroServicioCursoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServicioCursoApplication.class, args);
    }
}
