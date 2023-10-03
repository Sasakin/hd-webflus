package com.charot.dhwebflux.actuator.metrics.kafka;

import com.charot.dhwebflux.actuator.metrics.dto.AuthEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.DeserializationException;

import java.nio.charset.StandardCharsets;

@Log4j2
public class AuthEventDeserializer implements Deserializer<AuthEventDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AuthEventDto deserialize(String topic, byte[] data) {
        try {
            if (data == null || data.length == 0) {
                log.error("Null received at deserializing in topic {}", topic);
                return null;
            }
            String strData = new String(data, StandardCharsets.UTF_8);

            log.debug("The {} message received, validating and deserializing...", strData);

            JsonNode node = objectMapper.readTree(strData);

            return objectMapper.treeToValue(node, AuthEventDto.class);
        } catch (JsonProcessingException e) {
            log.error("Json parsing exception when deserialize message {},\n" +
                    "Error message: {}", new String(data, StandardCharsets.UTF_8), e.getMessage());
            throw new DeserializationException(e.getMessage(), data, false, e);
        }
    }

}
