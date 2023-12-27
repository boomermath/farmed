package com.boomermath.farmed.farm;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Records;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.boomermath.farmed.jooq.Tables.FARM;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmDataMapper farmDataMapper;
    private final DSLContext dsl;

    public Mono<FarmDTO> getFarm(UUID id) {
        return Mono.from(
                        dsl
                                .select(FARM.ID, FARM.NAME)
                                .from(FARM)
                ).map(Records.mapping(FarmDTO::new))
                .doOnNext(r -> log.info(r.toString()));
    }
}
