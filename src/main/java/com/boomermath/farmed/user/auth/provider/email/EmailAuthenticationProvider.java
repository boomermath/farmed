package com.boomermath.farmed.user.auth.provider.email;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.*;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class EmailAuthenticationProvider<T> implements AuthenticationProvider<T> {
    private final EmailAuthService emailAuthService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable T httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        log.info("RUN");
        EmailAuthDTO emailAuthDTO = new EmailAuthDTO(((String) authenticationRequest.getIdentity()), ((String) authenticationRequest.getSecret()));

        return emailAuthService.authenticate(emailAuthDTO)
                .flatMap(e -> 
                    Mono.just(
                        e.userAuthenticated() ? 
                        AuthenticationResponse.success(emailAuthDTO.email()) : 
                        AuthenticationResponse.failure(AuthenticationFailureReason.ACCOUNT_EXPIRED)
                    )
                );
    }
}
