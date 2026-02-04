package org.example.data.repositories;

import org.example.data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrasactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByAccountIdAndDateBetween(String accountId, LocalDate start, LocalDate end);
}
