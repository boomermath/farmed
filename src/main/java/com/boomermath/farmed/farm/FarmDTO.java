package com.boomermath.farmed.farm;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.UUID;

@Serdeable
@Introspected
@Data
public class FarmDTO {
    private final UUID id;
    private final String name;
    private final int rating;
    private final int reviewCount;
}
