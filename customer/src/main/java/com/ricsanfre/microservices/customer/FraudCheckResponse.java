package com.ricsanfre.microservices.customer;

public record FraudCheckResponse(
        Boolean isFraudster
) {
}
