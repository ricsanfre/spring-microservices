package com.ricsanfre.microservices.common;

public interface MessageProducer {

    public void publish(Object payload, String queueOrTopic, String key);
}
