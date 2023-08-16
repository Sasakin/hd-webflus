package com.charot.dhwebflux.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceObjectEntity {

    @Transient
    public static final String SEQUENCE_NAME = "resource_sequence";

    @Id
    private Long id;
    private String value;
    private String path;


}
