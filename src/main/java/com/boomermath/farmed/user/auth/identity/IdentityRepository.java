package com.boomermath.farmed.user.auth.identity;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface IdentityRepository extends ReactorCrudRepository<Identity, UUID> {
    @Join("user")
    Mono<Identity> findByHashAndIdentityType(String hash, IdentityType identityType);
}
