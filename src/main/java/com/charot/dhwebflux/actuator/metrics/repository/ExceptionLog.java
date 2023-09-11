package com.charot.dhwebflux.actuator.metrics.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "request_response_logs")
public class ExceptionLog {
    @Id
    private String id;
    private String exception;
}
