package com.charot.dhwebflux.repository;

import com.charot.dhwebflux.domain.entity.ResourceObjectEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ResourceObjectRepository extends ReactiveMongoRepository<ResourceObjectEntity, Long> {
}
