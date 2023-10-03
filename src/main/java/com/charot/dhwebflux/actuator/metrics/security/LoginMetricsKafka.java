package com.charot.dhwebflux.actuator.metrics.security;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginMetricsKafka {
    private final Counter successLoginCounter;
    private final Counter unsuccessLoginCounter;

    public LoginMetricsKafka(MeterRegistry meterRegistry) {
        successLoginCounter = meterRegistry.counter("login_success");
        unsuccessLoginCounter = meterRegistry.counter("login_unsuccess");
    }

    public void processLogin(boolean successful) {
        if (successful) {
            successLoginCounter.increment();
        } else {
            unsuccessLoginCounter.increment();
        }
    }
}
