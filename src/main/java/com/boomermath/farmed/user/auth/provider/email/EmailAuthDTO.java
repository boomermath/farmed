package com.boomermath.farmed.user.auth.provider.email;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Introspected
@Getter
@RequiredArgsConstructor
public final class EmailAuthDTO {
    private final String email;
    private final String username;
    private final String password;
}
