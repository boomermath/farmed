package com.boomermath.farmed.user.auth;

import io.micronaut.context.annotation.Context;
import io.micronaut.data.operations.PrimaryRepositoryOperations;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Context
@Slf4j
@RequiredArgsConstructor
public class TestService {
    private final PrimaryRepositoryOperations primaryRepositoryOperations;

    @PostConstruct
    public void test() {
        log.info(primaryRepositoryOperations.toString());
    }
}
