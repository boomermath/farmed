package com.boomermath.farmed.user.auth.provider.apple;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class AppleDTO {
    private final String code;
    private final String state;
}
