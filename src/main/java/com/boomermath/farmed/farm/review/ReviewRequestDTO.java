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
    @Min(1)
    @Max(5)
    private final int stars;
    private final String text;
}
