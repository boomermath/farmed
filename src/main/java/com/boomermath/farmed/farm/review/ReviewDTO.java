package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserDTO;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
@Introspected
@Data
public class ReviewDTO {
    private final UUID id;
    private final int stars;
    private final String text;
    private UserDTO user;
    private final LocalDateTime updatedAt;
}