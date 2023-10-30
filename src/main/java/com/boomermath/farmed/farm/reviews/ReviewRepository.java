package com.boomermath.farmed.farm.reviews;

import java.util.UUID;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;
import reactor.core.publisher.Mono;

@Repository
public interface ReviewRepository extends ReactorPageableRepository<Review, UUID> {
    Mono<Page<ReviewResponseDTO>> findByFarmIdOrderByUpdateAtDesc(UUID farmId, Pageable pageable);
    
    Mono<Boolean> existsByUserId(UUID userid);
    
    @Query("UPDATE review SET stars=:stars, text=:text WHERE id=:id AND user.id=:user.id")
    Mono<Review> updateOne(Review review);

    @Query("DELETE FROM Review WHERE id=:id AND user.id=:user.id")
    Mono<Long> deleteOne(Review review);
}
