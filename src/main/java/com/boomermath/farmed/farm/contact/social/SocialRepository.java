package com.boomermath.farmed.farm.contact.social;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

import java.util.UUID;

@Repository
public interface SocialRepository extends ReactorCrudRepository<Social, UUID> {
}
