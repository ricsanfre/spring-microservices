package com.ricsanfre.microservices.notification;

import com.ricsanfre.microservices.clients.notification.NotificationRequest;
import com.ricsanfre.microservices.clients.notification.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping()
    public NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest) {

        log.info("new notification received: {}", notificationRequest);
        return notificationService.sendNotification(notificationRequest);

    }

}
