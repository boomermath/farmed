package com.boomermath.farmed.farm.reviews;

import com.boomermath.farmed.user.data.User;
import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Introspected
@Getter
@RequiredArgsConstructor
public class ReviewResponseDTO {
    private final UUID id;
    private final int stars;
    private final String text;
    private final User user;
    private final LocalDateTime updatedAt;
}
