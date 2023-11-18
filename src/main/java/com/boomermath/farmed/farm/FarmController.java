package com.boomermath.farmed.farm;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
@Slf4j
public class FarmController {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Get
    public Mono<FarmDTO> farmInfo(@PathVariable UUID farmId, @QueryValue(defaultValue = "false") boolean contact, @QueryValue(defaultValue = "false") boolean schedule) {
        log.info("C: " + contact + " S:" + schedule);
        return farmRepository.findById(farmId, contact, schedule)
                .map(farmMapper::toDTO);
    }
}
