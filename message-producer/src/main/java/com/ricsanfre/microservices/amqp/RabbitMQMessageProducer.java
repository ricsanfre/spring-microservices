package com.ricsanfre.microservices.amqp;

import com.ricsanfre.microservices.common.MessageProducer;
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

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} exchange using routingKey {}. Payload: {}"
                , exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} exchange using routingKey {}. Payload: {}"
                , exchange, routingKey, payload);

    }


}
