package com.charot.dhwebflux.actuator.metrics.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExceptionLogRepository extends ReactiveMongoRepository<ExceptionLog, String> {
}
