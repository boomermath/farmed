package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.ContactMapper;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class FarmMapper implements FarmDataMapper {
    private final ContactMapper contactMapper;

    @Override
    public FarmDTO toDTO(Farm farm) {
        return FarmDTO.builder()
                .id(farm.getId())
                .name(farm.getName())
                .contact(contactMapper.toDTO(farm.getContact()))
                .rating(farm.getRating())
                .reviewCount(farm.getReviewCount())
                .build();
    }
}
