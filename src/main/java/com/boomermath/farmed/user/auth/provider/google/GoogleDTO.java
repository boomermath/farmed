package com.boomermath.farmed.user.auth.provider.google;


import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Introspected
@Getter
@AllArgsConstructor
public class GoogleDTO {
    @NotEmpty
    private final String idToken;
}
