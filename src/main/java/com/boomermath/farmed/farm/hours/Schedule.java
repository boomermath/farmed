package com.boomermath.farmed.farm.hours;


import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@MappedEntity
@Getter
@Setter
public class Schedule {
    @Id
    @AutoPopulated
    public UUID id;

    public DayOfWeek day;
    public LocalTime startTime;
    public LocalTime endTime;
}
