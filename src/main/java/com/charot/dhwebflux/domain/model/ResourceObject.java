package com.charot.dhwebflux.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceObject {

    private long id;
    private String value;
    private String path;

}
