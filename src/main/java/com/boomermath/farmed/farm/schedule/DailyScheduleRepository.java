package com.boomermath.farmed.farm.schedule;

import java.util.UUID;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

@Repository
public interface DailyScheduleRepository extends ReactorCrudRepository<DailySchedule, UUID> {
    }
