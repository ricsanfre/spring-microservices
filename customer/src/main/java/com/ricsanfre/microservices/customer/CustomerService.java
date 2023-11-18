package com.ricsanfre.microservices.customer;

import com.ricsanfre.microservices.clients.fraud.FraudCheckResponse;
import com.ricsanfre.microservices.clients.fraud.FraudClient;
import com.ricsanfre.microservices.clients.notification.NotificationClient;
import com.ricsanfre.microservices.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;

    public CustomerService(CustomerRepository customerRepository,
                           RestTemplate restTemplate, FraudClient fraudClient, NotificationClient notificationClient) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
    }


    public void registerCustomer(
            CustomerRegistrationRequest request) {

        // Create customer using builder pattern (lombok @Builder annotation)
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if mail is valid
        // todo: check if mail is not taken
        // Save customer. Save and flush so ge can obtain customer Id afterwards
        customerRepository.saveAndFlush(customer);
        // Check if fraudster using fraud microservice
        FraudCheckResponse fraudCheckResponse= fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        // Send Notification
        // todo: make it async (i.e using queue)
        NotificationRequest notification = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "My World",
                String.format("Hi %s, welcome to my World...",customer.getFirstName()),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        );
        notificationClient.sendNotification(notification);

    }
}
