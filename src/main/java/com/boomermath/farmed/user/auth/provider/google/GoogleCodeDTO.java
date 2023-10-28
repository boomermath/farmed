package com.boomermath.farmed.user.auth.provider.google;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class GoogleCodeDTO {
    private final String code;
}
