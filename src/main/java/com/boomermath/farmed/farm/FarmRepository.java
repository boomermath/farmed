package com.boomermath.farmed.farm;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;
import io.micronaut.data.hibernate.reactive.repository.jpa.ReactorJpaSpecificationExecutor;
import io.micronaut.data.jpa.repository.criteria.Specification;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface FarmRepository extends ReactorCrudRepository<Farm, UUID>, ReactorJpaSpecificationExecutor<Farm> {

    public class Specifications {
        public static Specification<Farm> idEquals(UUID id) {
            return (root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get(Farm_.id), id);
            };
        }
    }

    Mono<Farm> findByName(String name);

    @Override
    @Join("")
    Mono<Farm> findOne(Specification<Farm> specification);
}
