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

    @Join("farm")
    Mono<Review> findById(ReviewId id);

    @Join("farm")
    @Query("delete from review r where id=:id inner join Farm f on r.id.farmId=f.id")
    Mono<Review> delete(@Id ReviewId id);
}
