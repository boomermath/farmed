package com.boomermath.farmed.user.auth.provider.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Singleton
@RequiredArgsConstructor
public class GoogleClient {
    @Client("https://oauth2.googleapis.com")
    @Inject
    private final HttpClient httpClient;
    @Value("${micronaut.security.oauth2.clients.google.client-id}")
    private final String clientId;
    @Value("${micronaut.security.oauth2.clients.google.client-secret}")
    private final String clientSecret;
    private final GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance()).build();


    public Mono<GoogleIdToken.Payload> fetchTokenResponse(String code) {
        Map<String, String> googleTokenRequest = Map.of(
                "code", code,
                "client_id", clientId,
                "client_secret", clientSecret
        );

        HttpRequest<?> httpRequest = HttpRequest.POST("/token", googleTokenRequest);

        return Mono.from(httpClient.retrieve(httpRequest, Map.class))
                .map(resMap -> resMap.get("id_token"))
                .cast(String.class)
                .flatMap(idToken -> {
                    GoogleIdToken googleIdToken;

                    try {
                      googleIdToken  = googleIdTokenVerifier.verify(idToken);
                    } catch (Exception e) {
                        return Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.UNKNOWN));
                    }

                    return Mono.just(googleIdToken.getPayload());
                });
    }
}
