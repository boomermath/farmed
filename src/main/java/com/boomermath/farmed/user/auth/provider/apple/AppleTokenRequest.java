package com.boomermath.farmed.user.auth.provider.apple;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Serdeable
@Getter
@RequiredArgsConstructor
public class AppleTokenRequest {
    private final String clientId;
    private final String clientSecret;
    private final String code;
    private final String grantType = "authorization_code";
    //private final String refreshToken;
    //private final String redirectUri;
}
