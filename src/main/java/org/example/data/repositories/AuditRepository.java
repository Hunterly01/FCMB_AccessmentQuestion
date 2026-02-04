package org.example.data.repositories;

import org.example.data.models.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditLog,String> {
}
