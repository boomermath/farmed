package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.UserService;
import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.data.Identity;
import com.boomermath.farmed.user.auth.data.IdentityRepository;
import com.boomermath.farmed.user.auth.data.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthDTO;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class EmailAuthService implements AuthService<EmailAuthDTO> {
    private final IdentityRepository identityRepository;
    private final UserService userService;
    private final PasswordEncoder encoder;

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public Mono<AuthDTO> authenticate(EmailAuthDTO data) {
        return identityRepository.findByHashAndIdentityType(encoder.encode(data.getEmail() + data.getPassword()), IdentityType.EMAIL)
                .switchIfEmpty(Mono.error(AuthenticationResponse.exception(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH)))
                .map(e -> new AuthDTO(e.getUser().getId()));
    }

    @Override
    public Mono<AuthDTO> create(EmailAuthDTO data) {
        return userService.checkUserExistsOrCreate(data.getUsername(), data.getEmail(), encoder.encode(data.getEmail() + data.getPassword()), IdentityType.EMAIL);
    }

    @Override
    public Class<EmailAuthDTO> getDataType() {
        return EmailAuthDTO.class;
    }
}
