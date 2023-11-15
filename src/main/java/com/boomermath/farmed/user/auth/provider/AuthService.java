package com.boomermath.farmed.user.auth.provider;


import com.boomermath.farmed.user.auth.dto.UserRegisterDTO;
import com.boomermath.farmed.user.auth.identity.Identity;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthService<E> {

    E from(Map<String, String> attributes);

    Mono<Identity> authenticate(E data);
    Mono<Identity> create(E data, UserRegisterDTO registerDTO);
}
