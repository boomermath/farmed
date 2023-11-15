package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.auth.dto.UserRegisterDTO;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Introspected
@Getter
@AllArgsConstructor
public class GoogleCodeDTO {
    @NonNull
    private final String code;
}
