package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.auth.provider.UserRegisterDTO;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Introspected
@Getter
@AllArgsConstructor
public class GoogleCodeDTO implements UserRegisterDTO {
    @NonNull
    private final String code;
    private final String username;
}
