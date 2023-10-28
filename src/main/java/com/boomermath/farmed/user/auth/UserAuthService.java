package com.boomermath.farmed.user.auth;

import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.auth.provider.UserAuthenticationDTO;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.render.AccessRefreshToken;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class UserAuthService {
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;
    private final AccessRefreshTokenGenerator tokenGenerator;

    public Mono<Identity> checkUserExistsOrCreate(String username, String email, String hash, IdentityType identityType) {
        return userRepository.findByUsernameOrEmail(username, email)
                .flatMap(user -> Mono.error(AuthenticationResponse.exception(user.getEmail().equals(email) ? "EMAIL_EXISTS" : "USERNAME_EXISTS")))
                .switchIfEmpty(Mono.defer(() -> userRepository.save(new User(email, username))))
                .cast(User.class)
                .flatMap(u -> identityRepository.save(new Identity(identityType, hash, u)));
    }

    private Mono<AccessRefreshToken> identityToToken(Identity identity) {
        return Mono.just(identity)
                .map(i -> UserAuthenticationDTO.from(i.getUser()))
                .map(tokenGenerator::generate)
                .flatMap(Mono::justOrEmpty);
    }

    public <E> Mono<AccessRefreshToken> loginWithProvider(AuthService<E> authService, E dto) {
        return authService.authenticate(dto)
                .flatMap(this::identityToToken);
    }

    public <E> Mono<AccessRefreshToken> signupWithProvider(AuthService<E> authService, E dto, String username) {
        return authService.create(dto, username)
                .flatMap(this::identityToToken);
    }
}
