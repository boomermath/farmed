package com.boomermath.farmed.farm;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.hibernate.reactive.repository.jpa.ReactorJpaSpecificationExecutor;
import io.micronaut.data.jpa.repository.criteria.Specification;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface FarmRepository extends ReactorCrudRepository<Farm, UUID>, ReactorJpaSpecificationExecutor<Farm> {
    Mono<FarmDTO> find(@Id String id);
}
