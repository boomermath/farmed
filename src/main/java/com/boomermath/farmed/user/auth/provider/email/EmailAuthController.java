package com.boomermath.farmed.user.auth.provider.email;

import com.boomermath.farmed.user.auth.UserAuthService;
import com.boomermath.farmed.user.auth.provider.AuthController;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.render.AccessRefreshToken;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller("/user/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
public class EmailAuthController implements AuthController<EmailAuthDTO> {
    private final UserAuthService userAuthService;
    private final EmailAuthService emailAuthService;

    @Post("/email/login")
    public Mono<AccessRefreshToken> login(@Body EmailAuthDTO emailAuthDTO) {
        return userAuthService.loginWithProvider(emailAuthService, emailAuthDTO);
    }

    @Post("/email/signup")
    public Mono<AccessRefreshToken> signup(@Body Map<String, String> attributes) {
        return userAuthService.signupWithProvider(emailAuthService, new EmailAuthDTO(attributes.get("email"), attributes.get("password")), attributes.get("username"));
    }
}
