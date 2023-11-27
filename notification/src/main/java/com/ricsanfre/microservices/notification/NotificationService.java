package com.ricsanfre.microservices.notification;

import com.ricsanfre.microservices.clients.api.notification.NotificationResponse;
import com.ricsanfre.microservices.clients.message.common.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    public NotificationResponse sendNotification(Message message) {

        // todo: validate incoming data

        Notification notification = Notification.builder()
                .toCustomerId(message.toCustomerId())
                .toCustomerEmail(message.toCustomerEmail())
                .sender(message.sender())
                .message(message.message())
                .sentAt(LocalDateTime.parse(message.sentAt(),
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
