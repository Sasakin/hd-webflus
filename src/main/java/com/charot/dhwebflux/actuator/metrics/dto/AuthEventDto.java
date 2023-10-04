package com.charot.dhwebflux.actuator.metrics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthEventDto {
    @JsonProperty("authResultType")
    private AuthResultType authResultType;
    @JsonProperty("username")
    private String username;
    @JsonProperty("message")
    private String message;
}
