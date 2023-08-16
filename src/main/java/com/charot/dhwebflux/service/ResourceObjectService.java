package com.charot.dhwebflux.service;

import com.charot.dhwebflux.domain.entity.ResourceObjectEntity;
import com.charot.dhwebflux.domain.model.ResourceObject;
import com.charot.dhwebflux.repository.ResourceObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ResourceObjectService {

    private final ResourceObjectRepository repository;

    public Mono<Long> save(ResourceObject resourceObject) {
        return repository.save(new ResourceObjectEntity(
                resourceObject.getId(), resourceObject.getValue(),
                resourceObject.getPath())).map(ro -> ro.getId());

    }

    public Mono<ResourceObject> get(long id) {
        return repository.findById(id)
                .map(r -> new ResourceObject(r.getId(), r.getValue(), r.getPath()));
    }

    public Flux<ResourceObject> getAll() {
        return repository.findAll()
                .map(r -> new ResourceObject(r.getId(), r.getValue(), r.getPath()));
    }

}
