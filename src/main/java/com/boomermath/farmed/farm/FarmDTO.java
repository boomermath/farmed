package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.ContactDTO;
import com.boomermath.farmed.farm.schedule.DailyScheduleDTO;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Serdeable
@Introspected
@Data
public class FarmDTO {
    private final UUID id;
    private final String name;

  //  private final List<DailyScheduleDTO> schedule;
    private final ContactDTO contact;
}
