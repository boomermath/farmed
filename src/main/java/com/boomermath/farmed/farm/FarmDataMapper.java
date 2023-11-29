package com.boomermath.farmed.farm;

import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface FarmDataMapper {
    @Mapper
    FarmDTO toDTO(Farm farm);
}
