package com.charot.dhwebflux.actuator.metrics.security;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginMetrics {
    private final Counter successfulLoginsCounter;
    private final Counter failedLoginsCounter;

    public LoginMetrics(MeterRegistry meterRegistry) {
        successfulLoginsCounter = meterRegistry.counter("login.successful.count");
        failedLoginsCounter = meterRegistry.counter("login.failed.count");
    }

    public void incrementSuccessfulLogin() {
        successfulLoginsCounter.increment();
    }

    public void incrementFailedLogin() {
        failedLoginsCounter.increment();
    }
}
