package com.charot.dhwebflux.actuator.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionLogDto {
    private String exception;
}
