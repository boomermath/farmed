package com.boomermath.farmed.user.auth.provider.apple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppleTokenData {
    private final String email;
    private final String id;
}
