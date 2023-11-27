package com.ricsanfre.microservices.notification.rabbitmq;

import com.ricsanfre.microservices.clients.message.common.Message;
import com.ricsanfre.microservices.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value="message.broker.type",
        havingValue = "rabbitmq",
        matchIfMissing = true)
@Slf4j
public class NotificationRabbitMQConsumer {

    private final NotificationService notificationService;

    public NotificationRabbitMQConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(Message message) {
        log.info("Consumed {} from queue", message);
        notificationService.sendNotification(message);
    }
}
