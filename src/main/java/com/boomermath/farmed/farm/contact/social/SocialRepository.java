package com.boomermath.farmed.farm.contact.social;

import java.util.UUID;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

@Repository
public interface SocialRepository extends ReactorCrudRepository<Social, UUID> {
}
