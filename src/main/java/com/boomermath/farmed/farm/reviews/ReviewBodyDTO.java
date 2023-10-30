package com.boomermath.farmed.farm.reviews;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class ReviewBodyDTO {
    private final int stars;
    private final String text;
}
