package com.boomermath.farmed.farm.schedule;

import java.time.LocalTime;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable
@Introspected
@Data
public class DailyScheduleDTO {
    private final DayOfWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;
}
