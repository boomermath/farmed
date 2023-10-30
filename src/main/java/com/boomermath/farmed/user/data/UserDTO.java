package com.boomermath.farmed.user.data;

import java.util.UUID;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class UserDTO {
    private final UUID id;
    private final String username;
}
