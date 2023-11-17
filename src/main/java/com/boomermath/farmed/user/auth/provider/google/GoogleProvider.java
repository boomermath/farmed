package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.Provider;
import com.boomermath.farmed.user.User;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Singleton
@Named("google")
@RequiredArgsConstructor
public class GoogleProvider implements Provider<GoogleDTO> {
    private final GoogleClient googleClient;
    private final IdentityRepository identityRepository;

    @Override
    public GoogleDTO from(Map<String, String> attributes) {
        return new GoogleDTO(attributes.get("code"));
    }

    @Override
    public Mono<Identity> authenticate(@Valid GoogleDTO data) {
        return googleClient.fetchTokenResponse(data.getCode())
                .flatMap(token -> identityRepository.findByHashAndIdentityType(token.getSubject(), IdentityType.GOOGLE))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }


    @Override
    public Mono<Identity> create(@Valid GoogleDTO data) {
        return googleClient.fetchTokenResponse(data.getCode())
                .map(t -> Identity.builder()
                        .identityType(IdentityType.EMAIL)
                        .hash(t.getSubject())
                        .user(
                                User.builder()
                                        .email(t.getEmail())
                                        .build()
                        )
                        .build());
    }
}
