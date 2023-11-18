package com.boomermath.farmed.user.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterDTO {
    @NotEmpty
    private final String username;

    public static UserRegisterDTO from(Map<String, String> attributes) {
        return new UserRegisterDTO(attributes.get("username"));
    }
}
