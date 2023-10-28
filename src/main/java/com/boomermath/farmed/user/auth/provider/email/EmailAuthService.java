package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.auth.UserAuthService;
import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthService;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class EmailAuthService implements AuthService<EmailAuthDTO> {
    private final IdentityRepository identityRepository;
    private final UserAuthService userAuthService;
    private final PasswordEncoder encoder;

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public Mono<Identity> authenticate(EmailAuthDTO data) {
        return identityRepository.findByHashAndIdentityType(encoder.encode(data.getEmail() + data.getPassword()), IdentityType.EMAIL)
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)));
    }

    @Override
    public Mono<Identity> create(EmailAuthDTO data, String username) {
        return userAuthService.checkUserExistsOrCreate(username, data.getEmail(), encoder.encode(data.getEmail() + data.getPassword()), IdentityType.EMAIL);
    }
}
