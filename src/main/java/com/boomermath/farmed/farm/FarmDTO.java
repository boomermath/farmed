package com.boomermath.farmed.farm;

import java.util.UUID;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable
@Introspected
@Data
public class FarmDTO {
    private final UUID id;
    private final String name;
}
