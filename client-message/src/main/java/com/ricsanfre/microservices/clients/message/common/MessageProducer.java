package com.ricsanfre.microservices.clients.message.common;



public interface MessageProducer {

    public void publish(Message payload, String exchangeOrTopic, String key);
}
