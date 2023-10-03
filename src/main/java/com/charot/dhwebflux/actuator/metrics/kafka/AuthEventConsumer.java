package com.charot.dhwebflux.actuator.metrics.kafka;

import com.charot.dhwebflux.actuator.metrics.dto.AuthEventDto;
import com.charot.dhwebflux.actuator.metrics.dto.AuthResultType;
import com.charot.dhwebflux.actuator.metrics.security.LoginMetricsKafka;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Log4j2
@AllArgsConstructor
public class AuthEventConsumer {

    private final LoginMetricsKafka loginMetricsKafka;

    @KafkaListener(topics = {"${application.kafka.auth.topic}"})
    public void receive(AuthEventDto dto) {
        log.info("Received the next auth event: '{}'", dto);
        if (Objects.isNull(dto)) {
            log.error("Null received");
            throw new RuntimeException("Auth event is null");
        }
        loginMetricsKafka.processLogin(dto.getAuthResultType() == AuthResultType.SUCCESS);
    }
}
