package com.ricsanfre.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

// @EnableFeignClients. Use OpenFeign as REST Client
@SpringBootApplication(
        scanBasePackages = {
                "com.ricsanfre.microservices.customer",
                "com.ricsanfre.microservices.clients"
        }
)
@EnableFeignClients(
        basePackages = "com.ricsanfre.microservices.clients.api"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}