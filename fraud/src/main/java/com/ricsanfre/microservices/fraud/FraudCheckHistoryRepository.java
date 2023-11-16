package com.ricsanfre.microservices.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepository
        extends JpaRepository<FraudCheckHistory,Integer> {
}
