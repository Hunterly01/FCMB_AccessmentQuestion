package org.example.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
public class AuditLog {
    @Id
    private String id;
    private String username;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
