package com.boomermath.farmed.user.auth;


import com.boomermath.farmed.user.auth.provider.apple.AppleAuthService;
import com.boomermath.farmed.user.auth.provider.email.EmailAuthService;
import com.boomermath.farmed.user.auth.provider.google.GoogleAuthService;
import com.boomermath.farmed.user.data.UserRepository;
import com.boomermath.farmed.user.auth.provider.email.EmailAuthDTO;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;

@Controller("/user/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
public class UserAuthController {

    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final EmailAuthService emailAuthService;
    private final GoogleAuthService googleAuthService;
    private final AppleAuthService appleAuthService;
    private final int exp = 34000;

    @Post("/register/email")
    public AuthenticationResponse registerEmail(@Body EmailAuthDTO emailAuthDTO) {
        return emailAuthService.create(emailAuthDTO)
                .map(authDTO -> jwtTokenGenerator.generateToken(Authentication.build(authDTO.getId().toString()), exp))
                .map(jwt -> AuthenticationResponse.success(jwt));
    }
}
