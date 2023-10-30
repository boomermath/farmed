package com.boomermath.farmed.user.auth.provider.google;

import java.util.Map;

import com.boomermath.farmed.user.auth.UserAuthService;
import com.boomermath.farmed.user.auth.provider.AuthController;

import io.micronaut.security.token.render.AccessRefreshToken;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@Singleton
@RequiredArgsConstructor
public class GoogleAuthController implements AuthController<GoogleCodeDTO> {

    private final UserAuthService userAuthService;
    private final GoogleAuthService googleAuthService;

    @Override
    public Mono<AccessRefreshToken> login(GoogleCodeDTO dto) {
        return userAuthService.loginWithProvider(googleAuthService, dto);
    }

    @Override
    public Mono<AccessRefreshToken> signup(Map<String, String> attributes) {
        return userAuthService.signupWithProvider(googleAuthService, new GoogleCodeDTO(attributes.get("code")), attributes.get("username"));
    }
}
