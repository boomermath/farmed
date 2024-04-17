package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.ContactDTO;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Serdeable
@Introspected
@Data
@ToString
@Builder
public class FarmDTO {
    private final UUID id;
    private final String name;
    private final double rating;
    private final int reviewCount;
  //  private final List<DailyScheduleDTO> schedule;
   private final ContactDTO contact;
}
