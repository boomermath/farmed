package com.boomermath.farmed.user.data;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Serdeable
@Introspected
@Data
public class UserDTO {
    private final UUID id;
    private final String username;
}
