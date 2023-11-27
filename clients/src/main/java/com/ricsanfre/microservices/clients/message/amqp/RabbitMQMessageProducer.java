package com.ricsanfre.microservices.clients.message.amqp;

import com.ricsanfre.microservices.clients.message.common.Message;
import com.ricsanfre.microservices.clients.message.common.MessageProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value="message.broker.type",
        havingValue = "rabbitmq",
        matchIfMissing = true)
@Slf4j
@AllArgsConstructor
@Qualifier("rabbitmq")
public class RabbitMQMessageProducer implements MessageProducer {
    private final AmqpTemplate amqpTemplate;

    private void publishRabbitMQ(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} exchange using routingKey {}. Payload: {}"
                , exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} exchange using routingKey {}. Payload: {}"
                , exchange, routingKey, payload);

    }


    @Override
    public void publish(Message payload, String exchangeOrTopic, String key) {
        publishRabbitMQ(payload, exchangeOrTopic, key);
    }
}
