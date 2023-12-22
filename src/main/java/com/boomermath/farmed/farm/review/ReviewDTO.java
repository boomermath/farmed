package com.boomermath.farmed.farm.review;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
@Introspected
@Data
public class ReviewDTO {
    private final UUID id;
    private final int stars;
    private final String text;
    private final LocalDateTime timestamp;
    private final boolean updated;
}