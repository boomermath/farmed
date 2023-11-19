package com.boomermath.farmed.farm;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;
import io.micronaut.data.repository.jpa.reactive.ReactorJpaSpecificationExecutor;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface FarmRepository extends ReactorCrudRepository<Farm, UUID>, ReactorJpaSpecificationExecutor<Farm> {


    Mono<Farm> findByName(String name);



    default Mono<Farm> findById(@Id UUID id, boolean contact, boolean schedule) {
        QuerySpecification<Farm> joinSpec = (root, query, criteriaBuilder) -> {
//            if (contact) {
//                root.fetch("contact");
//            }
//            if (schedule) {
//                root.fetch("schedule");
//            }

            return criteriaBuilder.equal(root.get("id"), id);
        };

        return findOne(joinSpec);
    }
}
