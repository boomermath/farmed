package com.boomermath.farmed.farm.review;

import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Introspected
@Getter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class ReviewRequestDTO {
    @Min(1)
    @Max(5)
    private final int stars;
    private final String text;
    private final UUID userId;
    private final UUID farmId;
    private final UUID reviewId;
}
