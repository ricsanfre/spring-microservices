package com.ricsanfre.microservices.kafka;

import com.ricsanfre.microservices.common.MessageProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        value="message.broker.type",
        havingValue = "kafka",
        matchIfMissing = true)
@Slf4j
@AllArgsConstructor
@Qualifier("kafka")
public class KafkaMessageProducer implements MessageProducer {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void publish(Object payload, String topic, String key) {
        log.info("Publishing to {} topic. Payload: {}"
                , topic, payload);
        kafkaTemplate.send(topic, payload);
        log.info("Published to {} topic. Payload: {}"
                ,topic , payload);
    }

}
