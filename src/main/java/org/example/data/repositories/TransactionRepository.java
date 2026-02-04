package org.example.data.repositories;

import org.example.data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findBySourceAccount(String sourceAccount);
    List<Transaction> findBySourceAccountAndCreatedAtBetween(String sourceAccount, LocalDateTime start, LocalDateTime end);
}
