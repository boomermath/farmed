package com.boomermath.farmed;

import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

@Singleton
@RequiredArgsConstructor
public class FarmSeedingBean {
    private final DSLContext dsl;

    @EventListener
    public void start(ApplicationStartupEvent event) {
        return;
    }
}
