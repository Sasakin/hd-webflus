package com.charot.dhwebflux.repository;

import com.charot.dhwebflux.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    User findByUsername(String username);
}
