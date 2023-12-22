package com.boomermath.farmed.farm.schedule;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

import java.util.UUID;

@Repository
public interface DailyScheduleRepository extends ReactorCrudRepository<DailySchedule, UUID> {
}
