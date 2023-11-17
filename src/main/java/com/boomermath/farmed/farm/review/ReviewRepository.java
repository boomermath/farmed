package com.boomermath.farmed.farm.review;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface ReviewRepository extends ReactorPageableRepository<Review, UUID> {

    @Join("user")
    Mono<Page<Review>> findByFarmIdOrderByUpdatedAtDesc(UUID farmId, Pageable pageable);
    @Query("UPDATE review SET stars=:rev.stars, text=:rev.text WHERE id=:rev.reviewId AND user_id=:rev.userId AND farm_id=:rev.farmId")
    Mono<Review> updateOne(ReviewRequestDTO rev);

    @Query("DELETE FROM review WHERE id=:reviewId AND user_id=:userId")
    Mono<Long> deleteOne(@Id UUID reviewId, UUID userId);
}
