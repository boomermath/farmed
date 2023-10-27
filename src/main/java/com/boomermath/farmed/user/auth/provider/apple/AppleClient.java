package com.boomermath.farmed.user.auth.provider.apple;

import com.nimbusds.jose.jwk.JWKSet;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton
public class AppleClient {
    public static final String ISSUER = "https://appleid.apple.com";
    private final HttpClient httpClient;

    public AppleClient(@Client("https://appleid.apple.com/auth") HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    Mono<JWKSet> getPublicKeys() {
        return Mono.from(httpClient.retrieve(HttpRequest.GET("/keys"), JWKSet.class));
    }

    Mono<AppleTokenResponse> fetchToken(AppleTokenRequest appleTokenRequest) {
        HttpRequest<?> req = HttpRequest.POST("/token", AppleTokenResponse.class)
                .contentType(MediaType.FORM)
                .body(appleTokenRequest);

        return Mono.from(httpClient.retrieve(req, AppleTokenResponse.class));
    }
}
