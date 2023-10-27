package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.UserService;
import com.boomermath.farmed.user.auth.data.IdentityRepository;
import com.boomermath.farmed.user.auth.data.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthDTO;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.data.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.gson.GsonFactory;
import com.nimbusds.jose.jwk.JWKSet;
import io.micronaut.context.annotation.Value;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Singleton
@RequiredArgsConstructor
public class GoogleAuthService implements AuthService<GoogleDTO> {
    private final GoogleClient googleClient;
    @Value("${google.client-id}")
    private final String clientId;

    private final IdentityRepository identityRepository;
    private final UserService userService;

    @Override
    public String getType() {
        return "GOOGLE";
    }

    private Mono<GoogleIdToken.Payload> verifyToken(String token) {
        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(googleClient, GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();

        try {
            return Mono.just(googleIdTokenVerifier.verify(token).getPayload());
        } catch (GeneralSecurityException | IOException e) {
            return Mono.error(e);
        }
    }

    @Override
    public Mono<AuthDTO> authenticate(GoogleDTO data) {
        return verifyToken(data.getIdToken())
                .flatMap(token -> identityRepository.findByHashAndIdentityType(token.getSubject(), IdentityType.GOOGLE))
                .map(i -> new AuthDTO(i.getUser().getId()))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }

    @Override
    public Mono<AuthDTO> create(GoogleDTO data) {
        return verifyToken(data.getIdToken())
                .flatMap(token -> userService.checkUserExistsOrCreate(
                        data.getUsername(), token.getEmail(), token.getSubject(), IdentityType.GOOGLE
                ));
    }

    @Override
    public Class<GoogleDTO> getDataType() {
        return GoogleDTO.class;
    }
}
