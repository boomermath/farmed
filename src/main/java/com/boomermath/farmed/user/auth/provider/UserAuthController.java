package com.boomermath.farmed.user.auth.provider;


import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller("/user/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
@PermitAll
@RequiredArgsConstructor
public class UserAuthController<E extends UserRegisterDTO> {
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;
    private final AccessRefreshTokenGenerator tokenGenerator;
    private final Map<String, AuthService<E>> authServiceMap;


    public AuthService<E> getAuthService(String authMethod) {
        if (!authServiceMap.containsKey(authMethod)) {
            throw new AuthenticationException("INVALID_AUTH_METHOD");
        }

        return authServiceMap.get(authMethod);
    }

    @Post("/{authMethod}/login")
    @Secured(SecurityRule.IS_ANONYMOUS)
    @PermitAll
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
                .flatMap(i ->
                        Mono.just(i.getUser())
                                .flatMap(u ->
                                        userRepository.findByUsernameOrEmail(u.getUsername(), u.getEmail())
                                                .flatMap(user -> Mono.error(AuthenticationResponse.exception(user.getEmail().equals(u.getEmail()) ? "EMAIL_EXISTS" : "USERNAME_EXISTS")))
                                                .switchIfEmpty(Mono.defer(() ->
                                                        userRepository.save(u)
                                                                .flatMap(d -> identityRepository.save(i))
                                                                .map(d -> new UserAuthenticationDTO(u))
                                                                .map(tokenGenerator::generate)
                                                                .flatMap(Mono::justOrEmpty)
                                                        )).cast(AccessRefreshToken.class)
                                )
                );
    }
}
