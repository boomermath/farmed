package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.auth.provider.AuthDTO;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class EmailAuthService implements AuthService<EmailAuthDTO> {
    private final EmailUserRepository emailUserRepository;
    private final EmailUserMapper emailUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<AuthDTO> authenticate(EmailAuthDTO data) {
        return emailUserRepository.findByEmail(data.email())
                .map(emailUser -> {
                    if (passwordEncoder.matches(data.password(), emailUser.getPassword())) {
                        return new AuthDTO(true, null);
                    } else {
                        return new AuthDTO(false, AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
                    }
                }).switchIfEmpty(Mono.just(new AuthDTO(true, AuthenticationFailureReason.USER_NOT_FOUND)));
    }

    @Override
    public Mono<AuthDTO> create(EmailAuthDTO data) {
        return Mono.from(emailUserRepository.findByEmail(data.email()))
                .flatMap(__ -> Mono.just(new AuthDTO(false, AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)))
                .switchIfEmpty(
                        Mono.from(emailUserRepository.save(emailUserMapper.toEntity(data)))
                                .map(__ -> new AuthDTO(true, null))
                );
    }
}
