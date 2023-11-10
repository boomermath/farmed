package com.boomermath.farmed.user.data;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Introspected
@Getter
@RequiredArgsConstructor
public class UserDTO {
    private final UUID id;
    private final String username;
}
