package com.boomermath.farmed.farm.review;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Serdeable
@Introspected
@Getter
@RequiredArgsConstructor
public class ReviewRequestDTO {
    @Min(1)
    @Max(5)
    private final int stars;
    private final String text;
}
