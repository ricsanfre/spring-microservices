package com.ricsanfre.microservices.clients.api.fraud;

public record FraudCheckResponse(
        Boolean isFraudster
) {
}
