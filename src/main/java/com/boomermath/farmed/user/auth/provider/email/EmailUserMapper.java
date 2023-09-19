package com.boomermath.farmed.user.auth.provider.email;

import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface EmailUserMapper {
    @Mapper
    EmailUser toEntity(EmailAuthDTO emailAuthDTO);
}
