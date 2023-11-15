package com.boomermath.farmed.user.auth;

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

import com.boomermath.farmed.user.auth.dto.UserAuthenticationDTO;
import com.boomermath.farmed.user.auth.dto.UserRegisterDTO;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.provider.Provider;
import com.boomermath.farmed.user.data.UserRepository;

@Controller("/user/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
public class UserAuthController<E> {
    private final UserRepository userRepository;
    private final Map<String, Provider<E>> authServiceMap;



    @Post("/{authMethod}/login")
    public Mono<AccessRefreshToken> login(@Body Map<String, String> body, @PathVariable String authMethod) {
        Provider<E> authService = getAuthService(authMethod);

        return authService.authenticate(authService.from(body))
                .map(this::identityToToken);
    }

    @Post("/{authMethod}/register")
    public Mono<AccessRefreshToken> register(@Body Map<String, String> body, @PathVariable String authMethod) {
        Provider<E> authService = getAuthService(authMethod);

       UserRegisterDTO userRegisterDTO = UserRegisterDTO.from(body);
        
       return userRepository.existsByUsername(userRegisterDTO.getUsername())
            .filter(Boolean::booleanValue)
            .map(b -> AuthenticationResponse.exception("USERNAME_EXISTS"))
            .switchIfEmpty(
                Mono.defer(() -> 
                    authService.create(authService.from(body), userRegisterDTO)
                        .onErrorMap(R2dbcDataIntegrityViolationException.class, e -> AuthenticationResponse.exception("USER_EXISTS"))
                        .map(this::identityToToken)   
                )
            );
    }
}
