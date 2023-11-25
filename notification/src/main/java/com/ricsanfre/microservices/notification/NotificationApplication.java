package com.ricsanfre.microservices.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.ricsanfre.microservices.notification",
                "com.ricsanfre.microservices.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {

        SpringApplication.run(NotificationApplication.class, args);
    }

//    record Person (String name, Integer age) {}
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig config
//    ) {
//        return args -> {
//            producer.publish(
//                    new Person("Pepe", 22),
//                    config.getInternalExchange(),
//                    config.getInternalNotificationRoutingKey()
//            );
//
//
//        };
//    }
}