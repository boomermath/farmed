package com.boomermath.farmed.farm.reviews;

import java.time.LocalDateTime;
import java.util.UUID;

import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserDTO;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class ReviewResponseDTO {
    private final UUID id;
    private final int stars;
    private final String text;
    private final UserDTO user;
    private final LocalDateTime updatedAt;
}
