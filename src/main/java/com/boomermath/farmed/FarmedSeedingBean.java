package com.boomermath.farmed;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class FarmedSeedingBean {
    private final FarmRepository farmRepository;

    @EventListener
    public void appStart(ApplicationStartupEvent event) {
        Farm farm = Farm.builder()
        .name("Union")
        .build();

      //  farmRepository.save(farm).block();

    }
}
