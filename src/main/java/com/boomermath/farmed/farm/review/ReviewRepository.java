package com.boomermath.farmed.farm.review;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect =  Dialect.POSTGRES)
public interface ReviewRepository extends ReactorPageableRepository<Review, ReviewId> {

    @Join("user")
    Mono<Page<Review>> findByFarmIdOrderByUpdatedAtDesc(UUID farmId, Pageable pageable);

    @Join("farm")
    Mono<Review> findById(ReviewId id);
}
