package com.boomermath.farmed.user.auth.provider;


import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.http.HttpRequest;
import reactor.core.publisher.Mono;

public interface AuthService<E> {
    String getType();
    Mono<Identity> authenticate(E data);
    Mono<Identity> create(E data, String username);
    default boolean supports(HttpRequest<?> httpRequest) {
        return getType().equals(httpRequest.getAttribute("type").orElse(null));
    }
}
