package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.dto.UserRegisterDTO;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.Provider;
import com.boomermath.farmed.user.User;
import com.boomermath.farmed.user.UserAuthDTO;
import com.boomermath.farmed.user.UserRepository;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Singleton
@Named("email")
@RequiredArgsConstructor
public class EmailProvider implements Provider<EmailDTO> {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public EmailDTO from(Map<String, String> attributes) {
        return new EmailDTO(attributes.get("email"),  attributes.get("password"));
    }

    @Override
    public Mono<Identity> authenticate(@Valid EmailDTO data) {
        return userRepository.findByEmail(data.getEmail())
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.USER_NOT_FOUND)))
                .map(UserAuthDTO::getIdentity)
                .filter(i -> encoder.matches(data.getPassword(), i.getHash()))
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }

    @Override
    public Mono<Identity> create(@Valid EmailDTO data) {
        User newUser = User.builder()
                .email(data.getEmail())
                .build();

        Identity newUserLogin = Identity.builder()
                .identityType(IdentityType.EMAIL)
                .hash(encoder.encode(data.getPassword()))
                .user(newUser)
                .build();

        return Mono.just(newUserLogin);
    }
}
