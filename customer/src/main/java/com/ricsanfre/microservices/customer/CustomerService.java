package com.ricsanfre.microservices.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public CustomerService(CustomerRepository customerRepository,
                           RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
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
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        // todo: send Notification

    }
}
