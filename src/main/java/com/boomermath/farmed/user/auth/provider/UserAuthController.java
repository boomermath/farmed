package com.boomermath.farmed.user.auth.provider;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.*;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller("/user/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
public class UserAuthController<E extends UserRegisterDTO> {
    private final AccessRefreshTokenGenerator tokenGenerator;
    private final Map<String, AuthService<E>> authServiceMap;

    private AuthService<E> getAuthService(String authMethod) {
        if (!authServiceMap.containsKey(authMethod)) {
            throw new AuthenticationException("INVALID_AUTH_METHOD");
        }

        return authServiceMap.get(authMethod);
    }

    @Post("/{authMethod}/login")
    public Mono<AccessRefreshToken> login(@Body Map<String, String> body, @PathVariable String authMethod) {
        AuthService<E> authService = getAuthService(authMethod);

        return Mono.just(authService.from(body))
                .flatMap(authService::authenticate)
                .map(i -> new UserAuthenticationDTO(i.getUser()))
                .map(tokenGenerator::generate)
                .flatMap(Mono::justOrEmpty);
    }

    @Post("/{authMethod}/register")
    public Mono<AccessRefreshToken> register(@Body Map<String, String> body, @PathVariable String authMethod) {
        AuthService<E> authService = getAuthService(authMethod);

        return Mono.just(authService.from(body))
                .flatMap(authService::create)
                .onErrorMap(R2dbcDataIntegrityViolationException.class, e -> AuthenticationResponse.exception("USER_EXISTS"))
                .map(i -> new UserAuthenticationDTO(i.getUser()))
                .map(tokenGenerator::generate)
                .flatMap(Mono::justOrEmpty);
    }
}
