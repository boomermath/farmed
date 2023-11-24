package com.boomermath.farmed.farm;

import io.micronaut.data.jpa.repository.criteria.Specification;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.data.repository.jpa.criteria.QuerySpecification;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.boomermath.farmed.farm.FarmRepository.Specifications;

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
@Slf4j
public class FarmController {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Get
    @Transactional
    public Mono<FarmDTO> farmInfo(@PathVariable UUID farmId, @QueryValue(defaultValue = "false") boolean contact, @QueryValue(defaultValue = "false") boolean schedule) {
        return farmRepository.findOne(Specifications.idEquals(farmId))
                .map(farmMapper::toDTO);
    }
}
