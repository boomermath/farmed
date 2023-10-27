package com.boomermath.farmed.user.auth.provider;


import io.micronaut.http.HttpRequest;
import reactor.core.publisher.Mono;

public interface AuthService<E> {
    String getType();
    Mono<AuthDTO> authenticate(E data);
    Mono<AuthDTO> create(E data);

    Class<E> getDataType();

    default boolean supports(HttpRequest<?> httpRequest) {
        return getType().equals(httpRequest.getAttribute("type").orElse(null));
    }
}
