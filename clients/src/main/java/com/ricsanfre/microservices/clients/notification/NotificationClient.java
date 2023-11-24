package com.ricsanfre.microservices.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification",
        url = "${client.notification.url}"
)
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public interface NotificationClient {

    @PostMapping("api/v1/notification")
    public NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest);

}
