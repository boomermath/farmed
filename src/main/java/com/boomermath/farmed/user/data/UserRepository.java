package com.boomermath.farmed.user.data;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactorCrudRepository<User, UUID> {
    Mono<User> findByUsernameOrEmail(String username, String email);
}
