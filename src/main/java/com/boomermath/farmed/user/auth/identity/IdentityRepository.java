package com.boomermath.farmed.user.auth.identity;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface IdentityRepository extends ReactorCrudRepository<Identity, UUID> {
    @Join("user")
    Mono<Identity> findByHashAndIdentityType(String hash, IdentityType identityType);
}
