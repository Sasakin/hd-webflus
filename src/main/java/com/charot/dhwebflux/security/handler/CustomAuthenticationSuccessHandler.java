package com.charot.dhwebflux.security.handler;

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

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();

        metrics.incrementSuccessfulLogin();

        exchange.getResponse().setStatusCode(HttpStatus.OK);
        return exchange.getResponse().setComplete();
    }
}
