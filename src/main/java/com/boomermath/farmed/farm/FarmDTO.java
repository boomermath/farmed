package com.boomermath.farmed.farm;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Serdeable
@Introspected
@Data
@ToString
public class FarmDTO {
    private final UUID id;
    private final String name;

  //  private final List<DailyScheduleDTO> schedule;
  //  private final Contact contact;
}
