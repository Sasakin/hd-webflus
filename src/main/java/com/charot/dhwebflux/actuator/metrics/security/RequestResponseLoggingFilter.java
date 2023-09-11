package com.charot.dhwebflux.actuator.metrics.security;

import com.charot.dhwebflux.actuator.metrics.repository.RequestResponseLog;
import com.charot.dhwebflux.actuator.metrics.repository.RequestResponseLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@AllArgsConstructor
@Component
public class RequestResponseLoggingFilter implements WebFilter {

    private RequestResponseLogRepository repository;

    private ObjectMapper mapper;

    //@Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(securityContext -> {
                    // Получите запрос и ответ в виде строк
                    Mono<String> monoRequestString = null;
                    String responseString = null;
                    try {
                        monoRequestString = getRequestAsString(exchange);
                        responseString = getResponseAsString(exchange);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    // Создайте объект RequestResponseLog и сохраните его в MongoDB или другом месте

                    String finalResponseString = responseString;
                    monoRequestString.doOnNext(requestString -> {
                        RequestResponseLog log = new RequestResponseLog();
                        log.setId(generateUid());
                        log.setRequest(requestString);
                        log.setResponse(finalResponseString);
                        repository.save(log).subscribe();
                    }).subscribe();

                    return chain.filter(exchange);
                });
    }

    private String generateUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private Mono<String> getRequestAsString(ServerWebExchange request) throws JsonProcessingException {

        return request.getRequest().getBody()
                .map(dataBuffer -> {
                    try {
                        return dataBuffer.asInputStream()
                                .readAllBytes();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).map(bytes -> request.getRequest().getURI() + " " + new String(bytes, StandardCharsets.UTF_8))
                .defaultIfEmpty("").single();
    }

    private String getResponseAsString(ServerWebExchange response) throws JsonProcessingException {
        return response.getResponse().getStatusCode().toString();
    }

}
