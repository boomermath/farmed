package com.boomermath.farmed.user.auth.provider.apple;

import com.boomermath.farmed.user.auth.dto.UserRegisterDTO;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Introspected
@Getter
@AllArgsConstructor
public class AppleDTO implements UserRegisterDTO {
    private final String code;
    private final String state;
    private final String username;
}
