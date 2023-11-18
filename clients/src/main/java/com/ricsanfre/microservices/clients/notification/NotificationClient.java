package com.ricsanfre.microservices.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "notification"
)
public interface NotificationClient {

    @PostMapping("api/v1/notification")
    public NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest);

}
