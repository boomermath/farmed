package com.boomermath.farmed.user.auth.dto;

import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterDTO {
    @NotEmpty
    private final String username;

    public static UserRegisterDTO from(Map<String, String> attributes) {
        return new UserRegisterDTO(attributes.get("username"));
    }
}
