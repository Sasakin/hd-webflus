package com.charot.dhwebflux.actuator.metrics;

import com.charot.dhwebflux.actuator.metrics.dto.ExceptionLogDto;
import com.charot.dhwebflux.actuator.metrics.repository.ExceptionLog;
import com.charot.dhwebflux.actuator.metrics.repository.ExceptionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Endpoint(id = "exceptions")
@AllArgsConstructor
public class ExceptionsMetricsEndpoint {

    private ExceptionLogRepository repository;

    @ReadOperation
    public Flux<ExceptionLogDto> getMetrics() {
        Flux<ExceptionLog> logs = repository.findAll();
        return logs.map(log -> new ExceptionLogDto(log.getException()));
    }
}
