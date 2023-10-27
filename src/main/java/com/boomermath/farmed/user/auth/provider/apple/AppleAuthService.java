package com.boomermath.farmed.user.auth.provider.apple;

import com.boomermath.farmed.user.UserService;
import com.boomermath.farmed.user.auth.data.IdentityRepository;
import com.boomermath.farmed.user.auth.data.IdentityType;
import com.boomermath.farmed.user.auth.provider.AuthDTO;
import com.boomermath.farmed.user.auth.provider.AuthService;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTClaimsSetVerifier;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class AppleAuthService implements AuthService<AppleDTO> {
    private final UserService userService;
    private final IdentityRepository identityRepository;
    private final AppleClient appleClient;
    @Value("${apple.client-id}") private final String clientId;
    @Value("${apple.client-secret}") private final String clientSecret;
    @Override
    public String getType() {
        return "APPLE";
    }

    private Mono<AppleTokenData> verifyToken(AppleTokenResponse appleTokenResponse) {
        return appleClient.getPublicKeys()
                .map(keys -> {
                    ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
                    JWKSource<SecurityContext> keySource = (jwkSelector, context) -> keys.getKeys();
                    JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, keySource);
                    jwtProcessor.setJWSKeySelector(keySelector);
                    JWTClaimsSetVerifier<SecurityContext> claimsSetVerifier = new DefaultJWTClaimsVerifier<>(
                            new JWTClaimsSet.Builder()
                                    .issuer(AppleClient.ISSUER)
                                    .audience(clientId)
                                    .expirationTime(Date.from(Instant.now().plus(Integer.parseInt(appleTokenResponse.getExpiresIn()), ChronoUnit.SECONDS)))
                                    .build(), Collections.singleton("nonce")
                    );
                    jwtProcessor.setJWTClaimsSetVerifier(claimsSetVerifier);
                    JWTClaimsSet jwtClaimsSet;

                    try {
                        jwtClaimsSet = jwtProcessor.process(appleTokenResponse.getIdToken(), null);
                        return new AppleTokenData(jwtClaimsSet.getStringClaim("email"), jwtClaimsSet.getSubject());
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                }).cast(AppleTokenData.class);
    }

    @Override
    public Mono<AuthDTO> authenticate(AppleDTO data) {
        return appleClient.fetchToken(new AppleTokenRequest(clientId, clientSecret, data.getCode()))
                .flatMap(this::verifyToken)
                .flatMap(appleTokenData -> Mono.from(identityRepository.findByHashAndIdentityType(appleTokenData.getId(), IdentityType.APPLE)))
                .map(i -> new AuthDTO(i.getUser().getId()));
    }

    @Override
    public Mono<AuthDTO> create(AppleDTO data) {
        return appleClient.fetchToken(new AppleTokenRequest(clientId, clientSecret, data.getCode()))
                .flatMap(this::verifyToken)
                .flatMap(appleTokenData -> userService.checkUserExistsOrCreate(data.getUsername(), appleTokenData.getEmail(), appleTokenData.getId(), IdentityType.APPLE));
    }

    @Override
    public Class<AppleDTO> getDataType() {
        return AppleDTO.class;
    }
}
