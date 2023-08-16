package com.charot.dhwebflux.controller;

import com.charot.dhwebflux.domain.model.ResourceObject;
import com.charot.dhwebflux.service.ResourceObjectService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceObjectService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Long>> createResourceObject(@RequestBody ResourceObject object) {
        val result = service.save(object);
        return result.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/all")
    public Flux<ResourceObject> getResources() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResourceObject> getResourceObject(@PathVariable Integer id) {
        return service.get(id);
    }

}
