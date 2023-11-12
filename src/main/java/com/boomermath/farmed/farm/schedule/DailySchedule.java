package com.boomermath.farmed.farm.schedule;


import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

import com.boomermath.farmed.farm.Farm;

@MappedEntity
@Getter
@Setter
public class DailySchedule {
    @Id
    @AutoPopulated
    private UUID id;

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    private Farm farm;
}
