package com.boomermath.farmed.user.auth.provider;

import io.micronaut.security.token.render.AccessRefreshToken;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthController<E> {
    Mono<AccessRefreshToken> login(E dto);

    Mono<AccessRefreshToken> signup(Map<String, String> attributes);
}
