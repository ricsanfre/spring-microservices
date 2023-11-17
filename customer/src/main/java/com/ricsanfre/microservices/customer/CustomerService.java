package com.ricsanfre.microservices.customer;

import com.ricsanfre.microservices.clients.fraud.FraudCheckResponse;
import com.ricsanfre.microservices.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;

    public CustomerService(CustomerRepository customerRepository,
                           RestTemplate restTemplate, FraudClient fraudClient) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
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

        // todo: send Notification

    }
}
