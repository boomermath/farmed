package com.boomermath.farmed.farm.review;

import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class ReviewRequestDTO {
    private final int stars;
    @Min(1)
    @Max(5)
    private final String text;
}
