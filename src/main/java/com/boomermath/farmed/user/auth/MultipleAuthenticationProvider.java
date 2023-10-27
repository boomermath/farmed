package com.boomermath.farmed.user.auth;

import com.boomermath.farmed.user.auth.provider.AuthDTO;
import com.boomermath.farmed.user.auth.provider.AuthService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class MultipleAuthenticationProvider implements AuthenticationProvider<HttpRequest<?>> {

    private final List<AuthService<?>> authServices;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        AuthService<?> authService = authServices.stream()
                .filter(auth -> auth.supports(httpRequest))
                .findFirst()
                .orElseThrow(() -> AuthenticationResponse.exception(AuthenticationFailureReason.UNKNOWN));

        Mono<AuthDTO> authDTOMono = doAuth(authService, httpRequest.mutate().body(authService.getDataType()));

        return authDTOMono
                .map(authDTO -> AuthenticationResponse.success(authDTO.getId().toString()));
    }

    private <T> Mono<AuthDTO> doAuth(AuthService<T> authService, T data) {
        return authService.authenticate(data);
    }
}
