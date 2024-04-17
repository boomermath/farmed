package com.boomermath.farmed.farm.contact;

import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface ContactMapper {
    @Mapper
    ContactDTO toDTO(Contact contact);
}
