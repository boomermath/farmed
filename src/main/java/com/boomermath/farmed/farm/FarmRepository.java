package com.boomermath.farmed.farm;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.hibernate.reactive.repository.jpa.ReactorJpaSpecificationExecutor;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface FarmRepository extends ReactorCrudRepository<Farm, UUID>, ReactorJpaSpecificationExecutor<Farm> {
    Mono<Farm> find(@Id UUID id);
}
