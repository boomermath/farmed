package com.boomermath.farmed.farm;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller("/farm/{farmId}")
@RequiredArgsConstructor
public class FarmController {
    private final FarmRepository farmRepository;

    @Get("/info")
    public Mono<Farm> farmInfo(@PathVariable UUID farmId) {
        return farmRepository.findById(farmId);
    }
}
