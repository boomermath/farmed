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
    Mono<Page<ReviewDTO>> findByFarmIdOrderByUpdatedAtDesc(UUID farmId, Pageable pageable);
    
    Mono<Boolean> existsByUserId(UUID userId);
    
    @Query("UPDATE review SET stars=:newRev.stars, text=:newRev.text WHERE id=:reviewId AND user_id=:userId AND farm_id=:farmId")
    Mono<Review> updateOne(@Id UUID reviewId, UUID farmId, UUID userId, ReviewUpdateDTO newRev);

    @Query("DELETE FROM Review WHERE id=:reviewId AND user_id=:userId AND farm_id=:farmId")
    Mono<Long> deleteOne(@Id UUID reviewId, UUID farmId, UUID userId);
}
