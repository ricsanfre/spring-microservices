package com.ricsanfre.microservices.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value="module.kafka.enabled",
        havingValue = "true",
        matchIfMissing = true)
@Slf4j
@AllArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void publish(Object payload, String topic) {
        log.info("Publishing to {} topic. Payload: {}"
                , topic, payload);
        kafkaTemplate.send(topic, payload);
        log.info("Published to {} topic. Payload: {}"
                ,topic , payload);
    }

}
