package com.boomermath.farmed.user.auth;


import io.micronaut.http.annotation.Controller;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/user/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthController {
}
