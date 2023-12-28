package com.boomermath.jooq;

import io.micronaut.context.annotation.Factory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

@Factory
@RequiredArgsConstructor
public class JooqBean {

    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions
                        .parse("")
                        .mutate()
                        .option(ConnectionFactoryOptions.USER, "")
                        .option(ConnectionFactoryOptions.PASSWORD, "")
                        .build()
        );
    }
}
