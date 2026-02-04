package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("Transactions")
public class Transaction {
    @Id
    private String id;
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;
    private String sourceAccount;
    private BigDecimal discountAmount;
    private String destinationAccount;
    private TransactionType transactionType;

}
