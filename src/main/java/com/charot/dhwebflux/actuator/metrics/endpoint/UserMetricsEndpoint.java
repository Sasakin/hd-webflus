package com.charot.dhwebflux.actuator.metrics.endpoint;

import com.charot.dhwebflux.repository.UserRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Endpoint(id = "user-metrics")
@AllArgsConstructor
public class UserMetricsEndpoint {
    private final MeterRegistry meterRegistry;

    private final UserRepository userRepository;

    @ReadOperation
    public Mono<Long> getUserCount() {
        return userRepository.count()
                .doOnNext(cnt -> meterRegistry.gauge("user.count", cnt));
    }

}
