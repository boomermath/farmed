package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.Farm;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface FarmRepository extends ReactorCrudRepository<Farm, UUID> {
    Mono<Farm> findByName(String name);
}
