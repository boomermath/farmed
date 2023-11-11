package com.boomermath.farmed.user.auth.provider;

import jakarta.validation.constraints.NotBlank;

public interface UserRegisterDTO {
    @NotBlank
    String getUsername();
}
