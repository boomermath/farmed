package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.Contact;
import com.boomermath.farmed.farm.contact.ContactDTO;
import io.micronaut.context.annotation.Mapper;
import io.micronaut.context.annotation.Mapper.Mapping;
import jakarta.inject.Singleton;

@Singleton
public interface FarmDataMapper {
    @Mapper
    FarmDTO toDTO(Farm farm);
}
