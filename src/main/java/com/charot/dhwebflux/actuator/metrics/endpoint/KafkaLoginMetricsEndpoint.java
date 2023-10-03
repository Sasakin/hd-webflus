package com.charot.dhwebflux.actuator.metrics.endpoint;

import com.charot.dhwebflux.actuator.metrics.dto.LoginMetricsDto;
import com.charot.dhwebflux.actuator.metrics.security.LoginMetricsKafka;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "kafka-login-metrics")
@AllArgsConstructor
public class KafkaLoginMetricsEndpoint {

    private final LoginMetricsKafka loginMetricsKafka;

    @ReadOperation
    public LoginMetricsDto getMetrics() {
        return new LoginMetricsDto("Successful log in: " + loginMetricsKafka.getSuccessLoginCounter().count(),
                "Unsuccessful log in: " + loginMetricsKafka.getUnsuccessLoginCounter().count());
    }
}
