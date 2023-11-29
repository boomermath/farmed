package com.boomermath.farmed.farm;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
@Slf4j
public class FarmController {
    private final FarmRepository farmRepository;
    private final FarmDataMapper farmMapper;

    @Get
    public Mono<FarmDTO> farmInfo(@PathVariable String farmId) {
        return farmRepository.find(farmId);
    }
}
