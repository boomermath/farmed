package com.boomermath.farmed.farm;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class FarmController {
    private final FarmRepository farmRepository;

    @Get("/info")
    public Mono<FarmDTO> farmInfo(@PathVariable UUID farmId) {
        return farmRepository.findOneById(farmId);
    }
}
