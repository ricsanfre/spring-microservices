package com.ricsanfre.microservices.clients.api.notification;

import com.ricsanfre.microservices.clients.message.common.Message;

public record NotificationRequest(
        Message notification
) {
}
