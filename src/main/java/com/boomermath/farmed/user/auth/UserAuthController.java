package com.boomermath.farmed.user.auth;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.render.AccessRefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller("/user/auth/{authMethod}")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
@Slf4j
public class UserAuthController<E> {
    private final UserAuthService<E> userAuthService;

    @Post("/login")
    public Mono<AccessRefreshToken> login(@Body Map<String, String> body, @PathVariable String authMethod) {
        return userAuthService.authenticate(body, authMethod, false);
    }

    @Post("/register")
    public Mono<AccessRefreshToken> register(@Body Map<String, String> body, @PathVariable String authMethod) {
        return userAuthService.authenticate(body, authMethod, true);
    }
}
