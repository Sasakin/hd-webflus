package com.charot.dhwebflux.actuator.metrics;

import com.charot.dhwebflux.actuator.metrics.dto.LoginMetricsDto;
import com.charot.dhwebflux.actuator.metrics.security.LoginMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "login-metrics")
@AllArgsConstructor
public class LoginMetricsEndpoint {
    private final LoginMetrics loginMetrics;

    @ReadOperation
    public LoginMetricsDto getMetrics() {
        return new LoginMetricsDto(loginMetrics.getSuccessfulLoginsCounter().count() + "",
                loginMetrics.getFailedLoginsCounter().count() + "");
    }
}
