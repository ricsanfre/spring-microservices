package com.ricsanfre.microservices.notification;

import com.ricsanfre.microservices.clients.notification.NotificationRequest;
import com.ricsanfre.microservices.clients.notification.NotificationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {

        // todo: validate incoming data

        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender(notificationRequest.sender())
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.parse(notificationRequest.sentAt(),
                        DateTimeFormatter.ISO_DATE_TIME))
                .build();

        // save notification into database
        notificationRepository.saveAndFlush(notification);

        NotificationResponse response = new NotificationResponse(
                notification.getNotificationId(),
                notification.getToCustomerId(),
                notification.getToCustomerEmail(),
                notification.getSender(),
                notification.getMessage(),
                notification.getSentAt()
        );

        return response;


    }
}
