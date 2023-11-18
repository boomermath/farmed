package com.boomermath.farmed.farm.contact;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

import java.util.UUID;

@Repository
public interface ContactRepository extends ReactorCrudRepository<Contact, UUID> {
}
