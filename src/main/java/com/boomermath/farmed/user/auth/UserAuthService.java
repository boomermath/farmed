package com.boomermath.farmed.user.auth;

import java.util.Map;

import com.boomermath.farmed.user.auth.dto.UserAuthenticationDTO;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.data.UserRepository;

import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;

import com.boomermath.farmed.user.auth.provider.Provider;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class UserAuthService<E> {
    private final UserRepository userRepository;
    private final AccessRefreshTokenGenerator tokenGenerator;
    private final Map<String, Provider<E>> providers;

    private Mono<Provider<E>> getAuthService(String authMethod) {
        if (!providers.containsKey(authMethod)) {
            throw new AuthenticationException("INVALID_AUTH_METHOD");
        }

        return Mono.just(providers.get(authMethod));
    }

    private AccessRefreshToken identityToToken(Identity identity) {
        return tokenGenerator.generate(new UserAuthenticationDTO(identity.getUser())).orElseThrow();
    }

    public Mono<AccessRefreshToken> authenticate(Map<String, String> attributes, String authMethod, boolean isNewUser) {
       return getAuthService(authMethod)
       .map(a ->  {
        if (isNewUser) {
            return a.
        }
       })
    }
}
