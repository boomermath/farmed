package com.boomermath.farmed.farm.reviews;

import java.util.UUID;

import com.boomermath.farmed.farm.Farm;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

@Repository
public interface ReviewRepository extends ReactorPageableRepository<Review, UUID> {
    Mono<Page<Review>> findByFarmIdOrderByUpdateAtDesc(UUID farmId, Pageable pageable);
}
