package com.boomermath.farmed.user.auth.provider;

import io.micronaut.security.authentication.AuthenticationFailureReason;

public record AuthDTO(boolean userAuthenticated, AuthenticationFailureReason reason) {
}
