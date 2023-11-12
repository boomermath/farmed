package com.boomermath.farmed.farm.contact;

import java.util.UUID;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

@Repository
public interface ContactRepository extends ReactorCrudRepository<Contact, UUID> {
}
