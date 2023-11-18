package com.boomermath.farmed;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class FarmedSeedingBean {
    private final FarmRepository farmRepository;


    public void appStart(ApplicationStartupEvent event) {
        Farm farm = farmRepository.findByName("Union").block();

    }
}
