package com.boomermath.farmed.user.auth.provider.email;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface EmailUserRepository extends ReactiveStreamsCrudRepository<EmailUser, UUID> {
    Mono<EmailUser> findByEmail(String email);
}
