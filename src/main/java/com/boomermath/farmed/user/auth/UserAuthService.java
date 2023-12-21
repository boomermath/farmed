package com.boomermath.farmed.user.auth;

import com.boomermath.farmed.user.User;
import com.boomermath.farmed.user.UserRepository;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.provider.Provider;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class UserAuthService<E> {
    private final UserRepository userRepository;
    private final AccessRefreshTokenGenerator tokenGenerator;
    private final Map<String, Provider<E>> providers;

    public Mono<AccessRefreshToken> authenticate(Map<String, String> attributes, String authMethod, boolean isNewUser) {

        log.info(String.valueOf(providers.size()));

        for (String key: providers.keySet()) {
            log.info(key);
        }

        Provider<E> provider = Objects.requireNonNull(providers.get(authMethod), "INVALID_AUTH_METHOD");
        Mono<User> userMono;

        if (isNewUser) {
            String username = Objects.requireNonNull(attributes.get("username"), "USERNAME_REQUIRED");

            userMono = userRepository.existsByUsername(username)
                    .filter(b -> !b)
                    .switchIfEmpty( Mono.error(AuthenticationResponse.exception("USERNAME_EXISTS")))
                    .flatMap(b ->
                         provider.create(provider.from(attributes))
                                .map(i -> {
                                    i.getUser().setUsername(username);
                                    i.getUser().setIdentity(i);
                                    return i;
                                })
                                .flatMap(i -> userRepository.save(i.getUser()))
                                .onErrorMap(Exception.class, e -> AuthenticationResponse.exception("USER_EXISTS"))
                    );
        } else {
            userMono = provider.authenticate(provider.from(attributes))
                    .map(Identity::getUser);
        }

        return userMono
                .map(u -> tokenGenerator.generate(
                        Authentication.build(u.getUsername(), Map.of("id", u.getId()))
                ))
                .flatMap(Mono::justOrEmpty);
    }
}
