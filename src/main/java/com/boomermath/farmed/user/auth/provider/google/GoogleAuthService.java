package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Singleton
@Named("google")
@RequiredArgsConstructor
public class GoogleAuthService implements AuthService<GoogleCodeDTO> {
    private final GoogleClient googleClient;
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;

    @Override
    public GoogleCodeDTO from(Map<String, String> attributes) {
        return new GoogleCodeDTO(attributes.get("code"), attributes.get("username"));
    }

    @Override
    public Mono<Identity> authenticate(GoogleCodeDTO data) {
        return googleClient.fetchTokenResponse(data.getCode())
                .flatMap(token -> identityRepository.findByHashAndIdentityType(token.getSubject(), IdentityType.GOOGLE))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }


    @Override
    public Mono<Identity> create(GoogleCodeDTO data) {
        return googleClient.fetchTokenResponse(data.getCode())
                .map(t -> Identity.builder()
                        .identityType(IdentityType.EMAIL)
                        .hash(t.getSubject())
                        .user(
                                User.builder()
                                        .username(data.getUsername())
                                        .email(t.getEmail())
                                        .build()
                        )
                        .build())
                .flatMap(i ->
                        userRepository.save(i.getUser())
                                .flatMap(u -> identityRepository.save(i))
                );
    }
}
