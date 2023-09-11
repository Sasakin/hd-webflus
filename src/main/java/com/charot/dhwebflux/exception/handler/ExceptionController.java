package com.charot.dhwebflux.exception.handler;

import com.charot.dhwebflux.actuator.metrics.repository.ExceptionLog;
import com.charot.dhwebflux.actuator.metrics.repository.ExceptionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionController {

    private ExceptionLogRepository repository;

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleException(Exception ex) {
        ExceptionLog log = new ExceptionLog();
        log.setId(UUID.randomUUID().toString());
        log.setException(ex.toString());
        repository.save(log).subscribe();

        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()));
    }
}