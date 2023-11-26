package com.boomermath.farmed.user.auth.provider.google;

import com.boomermath.farmed.user.User;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.Provider;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
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
    private final IdentityRepository identityRepository;
    private final GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance()).build();


    private Mono<GoogleIdToken.Payload> verifyToken(String token){
        GoogleIdToken googleIdToken;

        try {
            googleIdToken = googleIdTokenVerifier.verify(token);
        } catch (Exception e) {
            return Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.UNKNOWN));
        }

        return Mono.just(googleIdToken.getPayload());
    }

    @Override
    public GoogleDTO from(Map<String, String> attributes) {
        return new GoogleDTO(attributes.get("id_token"));
    }

    @Override
    public Mono<Identity> authenticate(@Valid GoogleDTO data) {
        return verifyToken(data.getIdToken())
                .flatMap(token -> identityRepository.findByHashAndIdentityType(token.getSubject(), IdentityType.GOOGLE))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }


    @Override
    public Mono<Identity> create(@Valid GoogleDTO data) {
        return verifyToken(data.getIdToken())
                .map(t -> Identity.builder()
                        .identityType(IdentityType.EMAIL)
                        .hash(t.getSubject())
                        .user(
                                User.builder()
                                        .username((String) t.get("name"))
                                        .email(t.getEmail())
                                        .build()
                        )
                        .build());
    }
}
