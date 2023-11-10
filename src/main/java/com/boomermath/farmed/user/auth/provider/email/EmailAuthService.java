package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.data.User;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

@Singleton
@Named("email")
@RequiredArgsConstructor
@Slf4j
public class EmailAuthService implements AuthService<EmailAuthDTO> {
    private final IdentityRepository identityRepository;
    private final PasswordEncoder encoder;
    private final FarmRepository farmRepository;

    @Override
    public EmailAuthDTO from(Map<String, String> attributes) {
        return new EmailAuthDTO(attributes.get("email"), attributes.get("password"), attributes.get("username"));
    }

    @Override
    public Mono<Identity> authenticate(EmailAuthDTO data) {
        log.info(data.getEmail() + " " + data.getPassword());
        log.info(encoder.encode(data.getEmail() + data.getPassword()));
        return identityRepository.findByHashAndIdentityType(encoder.encode(data.getEmail() + data.getPassword()), IdentityType.EMAIL)
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }

    @Override
    public Mono<Identity> create(EmailAuthDTO data) {
        log.info(data.getEmail() + " " + data.getPassword());
        log.info(encoder.encode(data.getEmail() + data.getPassword()));
        return Mono.just(
                Identity.builder()
                        .identityType(IdentityType.EMAIL)
                        .hash(encoder.encode(data.getEmail() + data.getPassword()))
                        .user(
                                User.builder()
                                        .username(data.getUsername())
                                        .email(data.getEmail())
                                        .build()
                        )
                        .build()
        );
    }

    @EventListener
    @Transactional
    public void startupEvent(StartupEvent startupEvent) {
//        Farm farm = Farm.builder()
//                .name("Union")
//                .build();
//
//        farmRepository.save(farm).block();
    }
}
