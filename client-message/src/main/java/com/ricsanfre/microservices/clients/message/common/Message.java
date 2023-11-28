package com.ricsanfre.microservices.clients.message.common;

public record Message(
        Integer toCustomerId,
        String toCustomerEmail,
        String sender,
        String message,
        String sentAt) {
}
