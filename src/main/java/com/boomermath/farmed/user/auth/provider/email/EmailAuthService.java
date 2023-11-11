package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

@Singleton
@Named("email")
@RequiredArgsConstructor
@Slf4j
public class EmailAuthService implements AuthService<EmailAuthDTO> {
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;
    private final PasswordEncoder encoder;

    @Override
    public EmailAuthDTO from(Map<String, String> attributes) {
        return new EmailAuthDTO(attributes.get("email"), attributes.get("password"), attributes.get("username"));
    }

    @Override
    public Mono<Identity> authenticate(@Valid EmailAuthDTO data) {
        return userRepository.findByEmail(data.getEmail())
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.USER_NOT_FOUND)))
                .map(User::getIdentity)
                .filter(i -> encoder.matches(data.getPassword(), i.getHash()))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }

    @Override
    public Mono<Identity> create(@Valid EmailAuthDTO data) {
        User newUser = User.builder()
                .username(data.getUsername())
                .email(data.getEmail())
                .build();

        Identity newUserLogin = Identity.builder()
                .identityType(IdentityType.EMAIL)
                .hash(encoder.encode(data.getPassword()))
                .user(newUser)
                .build();

        return userRepository.save(newUser)
                .flatMap(u -> identityRepository.save(newUserLogin));
    }
}
