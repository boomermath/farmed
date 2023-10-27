package com.boomermath.farmed.user.auth.data;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface IdentityRepository extends ReactiveStreamsCrudRepository<Identity, UUID> {
    @Join("user")
    Mono<Identity> findByHashAndIdentityType(String hash, IdentityType identityType);
}
