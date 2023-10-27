package com.boomermath.farmed.user.auth.provider.apple;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Introspected
@Getter
@RequiredArgsConstructor
public class AppleTokenResponse {
    private final String accessToken;
    private final String expiresIn;
    private final String idToken;
    private final String refreshToken;
}
