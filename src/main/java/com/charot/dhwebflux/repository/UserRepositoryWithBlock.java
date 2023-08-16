package com.charot.dhwebflux.repository;

import com.charot.dhwebflux.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryWithBlock extends MongoRepository<User, Long> {
}
