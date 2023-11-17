package com.ricsanfre.microservices.clients.fraud;

public record FraudCheckResponse(
        Boolean isFraudster
) {
}
