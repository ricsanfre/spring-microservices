package com.ricsanfre.microservices.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotations
// @Builder https://projectlombok.org/features/Builder
// @Data https://projectlombok.org/features/Data. Getter/Setters, toString, hash, equal methods
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "customer_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
