package com.boomermath.farmed.farm;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Singleton
@RequiredArgsConstructor
@Slf4j
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    public Mono<FarmDTO> getFarm(UUID id) {
        return Mono.empty();
    }
}
