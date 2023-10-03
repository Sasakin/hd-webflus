package com.charot.dhwebflux.security.handler;

import com.charot.dhwebflux.actuator.metrics.dto.AuthEventDto;
import com.charot.dhwebflux.actuator.metrics.dto.AuthResultType;
import com.charot.dhwebflux.actuator.metrics.kafka.AuthEventProducer;
import com.charot.dhwebflux.actuator.metrics.security.LoginMetrics;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private final LoginMetrics metrics;

    private final AuthEventProducer producer;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();

        metrics.incrementSuccessfulLogin();

        producer.sendAuthEvent(new AuthEventDto(AuthResultType.SUCCESS, authentication.getName(), ""));

        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return exchange.getResponse().setComplete();
    }
}
