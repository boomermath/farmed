package com.boomermath.farmed.user;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Repository
public interface UserRepository extends ReactorCrudRepository<User, UUID> {

    @Join("identity")
    Mono<User> findByEmail(String email);

    Mono<Boolean> existsByUsername(String username);
}
