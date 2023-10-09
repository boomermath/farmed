package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.provider.AuthDTO;
import com.boomermath.farmed.user.auth.provider.AuthService;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.validation.validator.Validator;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class EmailAuthService implements AuthService<EmailAuthDTO> {
    private final EmailUserRepository emailUserRepository;
    private final EmailUserMapper emailUserMapper;
    private final PasswordEncoder passwordEncoder;

    private Mono<Error> validatePassword(String raw, String actual) {
        if (passwordEncoder.matches(raw, actual)) {
            return Mono.empty();
        } else {
            return Mono.error(new AuthenticationException("Bad pass"));
        }
    }

    @Override
    public Mono<AuthDTO> authenticate(EmailAuthDTO data) {
        return emailUserRepository.findByEmail(data.email())
                .filter(emailUser -> passwordEncoder.matches(data.password(), emailUser.getPassword()))
                .switchIfEmpty(Mono.error(new AuthenticationException("Invalid")))
                .map(e -> new AuthDTO(e.getUser().getUsername()));
    }

    @Override
    public Mono<AuthDTO> create(EmailAuthDTO data) {
        return emailUserRepository.findByEmail(data.email())
                .flatMap(e -> Mono.error(new AuthenticationException("User exists")))
                .switchIfEmpty(
                        Mono.from(emailUserRepository.save(emailUserMapper.toEntity(data)))
                                .map(e -> new AuthDTO(e.getUser().getUsername()))
                )
                .cast(AuthDTO.class);
    }
}
