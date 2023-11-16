package com.boomermath.farmed.user.auth;

import com.boomermath.farmed.user.User;
import com.boomermath.farmed.user.UserRepository;
import com.boomermath.farmed.user.auth.dto.UserAuthenticationDTO;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.provider.Provider;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Map;
import java.util.Objects;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class UserAuthService<E> {
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;
    private final AccessRefreshTokenGenerator tokenGenerator;
    private final Map<String, Provider<E>> providers;

    @Transactional
    public Mono<AccessRefreshToken> authenticate(Map<String, String> attributes, String authMethod, boolean isNewUser) {
        Provider<E> provider = Objects.requireNonNull(providers.get(authMethod), "INVALID_AUTH_METHOD");
        Mono<User> userMono;

        if (isNewUser) {
            String username = Objects.requireNonNull(attributes.get("username"), "USERNAME_REQUIRED");

            userMono = userRepository.existsByUsername(username)
                    .flatMap(b -> {
                        if (b) {
                            return Mono.error(AuthenticationResponse.exception("USERNAME_EXISTS"));
                        }

                        return provider.create(provider.from(attributes))
                                .map(i -> {
                                    i.getUser().setUsername(username);
                                    return i;
                                })
                                .flatMap(i -> Mono.zip(userRepository.save(i.getUser()), identityRepository.save(i)))
                                .onErrorMap(R2dbcDataIntegrityViolationException.class, e -> AuthenticationResponse.exception("USER_EXISTS"))
                                .map(Tuple2::getT1);
                    });
        } else {
            userMono = provider.authenticate(provider.from(attributes))
                    .map(Identity::getUser);
        }

        return userMono
                .map(u -> tokenGenerator.generate(new UserAuthenticationDTO(u)))
                .flatMap(Mono::justOrEmpty);
    }
}
