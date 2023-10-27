package com.boomermath.farmed.user.auth.provider.google;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Singleton
@RequiredArgsConstructor
public class GoogleClient extends HttpTransport {
    private final HttpClient httpClient;
    @Override
    protected LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return httpClient.toBlocking().retrieve(HttpRequest.create(HttpMethod.parse(method), url), LowLevelHttpRequest.class);
    }
}
