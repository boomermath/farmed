package com.boomermath.farmed.user;

import com.boomermath.farmed.user.auth.data.Identity;
import com.boomermath.farmed.user.auth.data.IdentityRepository;
import com.boomermath.farmed.user.auth.data.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthDTO;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;

    public Mono<AuthDTO> checkUserExistsOrCreate(String username, String email, String hash, IdentityType identityType) {
        return userRepository.findByUsernameOrEmail(username, email)
                .flatMap(user -> Mono.error(AuthenticationResponse.exception(user.getEmail().equals(email) ? "EMAIL_EXISTS" : "USERNAME_EXISTS")))
                .switchIfEmpty(Mono.defer(() -> Mono.from(userRepository.save(new User(email, username)))))
                .cast(User.class)
                .flatMap(u -> Mono.from(identityRepository.save(new Identity(identityType, hash, u))))
                .map(i -> new AuthDTO(i.getUser().getId()));
    }
}
