package com.boomermath.farmed.farm.review;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ReviewRepository extends ReactorPageableRepository<Review, UUID> {

    @Join("user")
    Mono<Page<Review>> findByFarmIdOrderByUpdatedAtDesc(UUID farmId, Pageable pageable);

    @Join("farm")
    Mono<Review> findById(UUID id);

    @Join("farm")
    Mono<Void> deleteByIdAndFarmIdAndUserId(@Id UUID id, UUID farmId, UUID userId);
}
