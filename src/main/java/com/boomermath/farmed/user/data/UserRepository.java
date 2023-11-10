package com.boomermath.farmed.user.data;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface UserRepository extends ReactorCrudRepository<@Valid User, UUID> {
    Mono<User> findByUsernameOrEmail(String username, String email);
}
