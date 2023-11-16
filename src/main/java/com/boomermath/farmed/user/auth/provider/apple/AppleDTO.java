package com.boomermath.farmed.user.auth.provider.apple;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Introspected
@Getter
@AllArgsConstructor
public class AppleDTO {
    private final String code;
    private final String state;
    private final String username;
}
