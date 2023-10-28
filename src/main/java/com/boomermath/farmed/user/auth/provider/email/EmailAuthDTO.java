package com.boomermath.farmed.user.auth.provider.email;


import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Introspected
@Getter
@RequiredArgsConstructor
public final class EmailAuthDTO {
    private final String email;
    private final String password;
}
