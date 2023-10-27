package com.boomermath.farmed;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.HashMap;
import java.util.Map;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class TestController {
    @Produces(MediaType.APPLICATION_JSON)
    @Get
    public Map<String, String> index() {
        return Map.of("hi", "hello");
    }
}
