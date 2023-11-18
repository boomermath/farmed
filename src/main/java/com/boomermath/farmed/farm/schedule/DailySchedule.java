package com.boomermath.farmed.farm.schedule;


import com.boomermath.farmed.farm.Farm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DailySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    private Farm farm;
}
