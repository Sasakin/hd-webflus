package com.charot.dhwebflux.actuator.metrics.kafka;

import com.charot.dhwebflux.actuator.metrics.dto.AuthEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${application.kafka.auth.topic}")
    private String topic;

    public void sendAuthEvent(AuthEventDto authEvent) {
        // Convert the AuthEvent object to JSON or any other desired format
        String authEventJson = convertAuthEventToJson(authEvent);

        // Send the Auth event to the "auth-events" topic
        kafkaTemplate.send(topic, authEventJson);
    }

    private String convertAuthEventToJson(AuthEventDto authEvent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(authEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Exception processing convert AuthEvent to json.", e);
        }
    }

}
