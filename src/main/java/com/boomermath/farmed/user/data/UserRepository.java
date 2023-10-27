package com.boomermath.farmed.user.data;

import com.boomermath.farmed.user.data.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveStreamsCrudRepository<User, UUID> {
    Mono<User> findByUsernameOrEmail(String username, String email);

}
