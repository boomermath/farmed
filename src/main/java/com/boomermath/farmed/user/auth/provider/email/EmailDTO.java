package com.boomermath.farmed.user.auth.provider.email;


import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Introspected
@Getter
@AllArgsConstructor
public final class EmailDTO {
    @Email
    private final String email;
    @NotEmpty
    private final String password;
    @NotEmpty
    private final String username;
}
