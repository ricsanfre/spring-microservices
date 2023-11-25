package com.ricsanfre.microservices.notification.kafka;

import com.ricsanfre.microservices.clients.notification.NotificationRequest;
import com.ricsanfre.microservices.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(
        value="message.broker.type",
        havingValue = "kafka",
        matchIfMissing = true)
public class NotificationKafkaConsumer {

    private final NotificationService notificationService;

    public NotificationKafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "${kafka.notification.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(String message) {
        log.info("Consumed {} from topic", message);
        // notificationService.sendNotification(notificationRequest);
    }
}
