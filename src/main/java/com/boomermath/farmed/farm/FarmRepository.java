package com.boomermath.farmed.farm;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface FarmRepository extends ReactorCrudRepository<Farm, UUID> {
    Mono<FarmDTO> find(@Id String id);
}
