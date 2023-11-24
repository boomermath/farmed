package com.boomermath.farmed.user.auth.provider.google;


import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Introspected
@Getter
@AllArgsConstructor
public class GoogleDTO {
    @NonNull
    private final String idToken;
}
