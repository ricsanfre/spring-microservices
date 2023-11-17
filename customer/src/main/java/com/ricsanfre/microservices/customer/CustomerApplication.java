package com.ricsanfre.microservices.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// @EnableDiscoveryClient. Use Eurekaserver for discovering other microservices
// @EnableFeignClients. Use OpenFeign as REST Client
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.ricsanfre.microservices.clients"
)
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}