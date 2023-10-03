package com.charot.dhwebflux.actuator.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthEventDto {
    private AuthResultType authResultType;
    private String username;
    private String message;
}
