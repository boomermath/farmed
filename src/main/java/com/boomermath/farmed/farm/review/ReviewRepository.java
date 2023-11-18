package com.boomermath.farmed.farm.review;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ReviewRepository extends ReactorPageableRepository<Review, ReviewId> {

    @Join("user")
    Flux<Review> findByFarmIdOrderByUpdatedAtDesc(UUID farmId, Pageable pageable);

    @Query("UPDATE Review SET stars=:rev.stars, text=:rev.text WHERE id=:compositeId.id AND user_id=:compositeId.userId AND farm_id=:compositeId.farmId")
    Mono<Review> updateOne(@Id ReviewId compositeId, ReviewRequestDTO rev);
}
