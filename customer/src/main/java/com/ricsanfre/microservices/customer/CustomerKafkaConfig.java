package com.ricsanfre.microservices.customer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(
        value="module.kafka.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class CustomerKafkaConfig {

    @Value("${kafka.notification.topic}")
    private String notificationTopic;
    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder.name(notificationTopic).build();
    }
}
