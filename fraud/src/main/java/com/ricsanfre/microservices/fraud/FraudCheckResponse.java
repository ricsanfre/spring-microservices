package com.ricsanfre.microservices.fraud;

public record FraudCheckResponse (
        Boolean isFraudster
) {
}
