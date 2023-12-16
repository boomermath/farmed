package com.boomermath.farmed.farm;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmDataMapper farmDataMapper;
}
