package com.boomermath.farmed.user;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.UUID;

@Serdeable
@Introspected
@Data
public class UserDTO {
    private final UUID id;
    private final String username;
}
