package com.charot.dhwebflux.actuator.metrics.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RequestResponseLogRepository extends ReactiveMongoRepository<RequestResponseLog, String> {
}
