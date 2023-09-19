package com.boomermath.farmed.user.auth.provider.email;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.*;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class EmailAuthenticationProvider<T> implements AuthenticationProvider<T> {
    private final EmailAuthService emailAuthService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable T httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        EmailAuthDTO emailAuthDTO = new EmailAuthDTO(null, ((String) authenticationRequest.getIdentity()), ((String) authenticationRequest.getSecret()));

        return emailAuthService.authenticate(emailAuthDTO)
                .flatMap(e -> {
                    if (e.userAuthenticated()) {
                        return Mono.just(AuthenticationResponse.failure(e.reason()));
                    } else {
                        return Mono.just(AuthenticationResponse.success(emailAuthDTO.email()));
                    }
                });
    }
}
