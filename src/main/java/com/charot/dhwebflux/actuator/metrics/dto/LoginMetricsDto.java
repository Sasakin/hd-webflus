package com.charot.dhwebflux.actuator.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMetricsDto {
    private String successLoginCount;
    private String failLoginCount;
}
