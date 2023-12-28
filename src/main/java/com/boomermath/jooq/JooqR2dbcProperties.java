package com.boomermath.jooq;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties("r2dbc.datasources")
@Data
public class JooqR2dbcProperties {
    private String url;
    private String username;
    private String password;
}
