package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.auth.UserAuthService;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthService;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class GoogleAuthService implements AuthService<GoogleCodeDTO> {
    private final GoogleClient googleClient;
    private final IdentityRepository identityRepository;
    private final UserAuthService userAuthService;

    @Override
    public String getType() {
        return "GOOGLE";
    }


    @Override
    public Mono<Identity> authenticate(GoogleCodeDTO data) {
        return googleClient.fetchTokenResponse(data.getCode())
                .flatMap(token -> identityRepository.findByHashAndIdentityType(token.getSubject(), IdentityType.GOOGLE))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }

    @Override
    public Mono<Identity> create(GoogleCodeDTO data, String username) {
        return googleClient.fetchTokenResponse(data.getCode())
                .flatMap(token -> userAuthService.checkUserExistsOrCreate(
                        username, token.getEmail(), token.getSubject(), IdentityType.GOOGLE
                ));
    }
}
