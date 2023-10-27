package com.boomermath.farmed.user.auth.provider;


import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Serdeable
@RequiredArgsConstructor
@Getter
public class AuthDTO {
    private final UUID id;
}
