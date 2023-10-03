package com.charot.dhwebflux.actuator.metrics.endpoint;

import com.charot.dhwebflux.actuator.metrics.dto.RequestResponseDto;
import com.charot.dhwebflux.actuator.metrics.repository.RequestResponseLog;
import com.charot.dhwebflux.actuator.metrics.repository.RequestResponseLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Endpoint(id = "request-response")
@AllArgsConstructor
public class RequestesResponseMetricsEndpoint {

    private RequestResponseLogRepository repository;

    @ReadOperation
    public Flux<RequestResponseDto> getMetrics() {
        Flux<RequestResponseLog> logs = repository.findAll();
        return logs.map(log -> new RequestResponseDto(log.getRequest(), log.getResponse()));
    }
}
