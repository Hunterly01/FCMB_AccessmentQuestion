package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("Transactions")
public class Transaction {
    @Id
    private String id;
    private String accountId;
    private double amount;
    private LocalDateTime date;
    private String description;
    private double discount;
    private TransactionType transactionType;


    public enum TransactionType{
        TRANSFER,
        AIRTIME

    }
}
