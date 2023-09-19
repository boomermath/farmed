package com.boomermath.farmed.user.auth.provider;


import reactor.core.publisher.Mono;

public interface AuthService<E> {
    Mono<AuthDTO> authenticate(E data);
    Mono<AuthDTO> create(E data);
}
