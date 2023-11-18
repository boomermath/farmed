package com.boomermath.farmed.user.auth.provider.apple;

import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.provider.Provider;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTClaimsSetVerifier;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
public class AppleAuthService implements Provider<AppleDTO> {
    private final IdentityRepository identityRepository;
    private final AppleClient appleClient;
    //   @Value("${apple.client-id}")
    private final String clientId;
    //    @Value("${apple.client-secret}")
    private final String clientSecret;

    private Mono<AppleTokenData> verifyToken(AppleTokenResponse appleTokenResponse) {
        return appleClient.getPublicKeys()
                .flatMap(keys -> {
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

                    try {
                        JWTClaimsSet jwtClaimsSet = jwtProcessor.process(appleTokenResponse.getIdToken(), null);
                        return Mono.just(new AppleTokenData(jwtClaimsSet.getStringClaim("email"), jwtClaimsSet.getSubject()));
                    } catch (Exception e) {
                        return Mono.error(e);
                    }
                });
    }

    @Override
    public AppleDTO from(Map<String, String> attributes) {
        return new AppleDTO(attributes.get("code"), attributes.get("state"), attributes.get("username"));
    }

    @Override
    public Mono<Identity> authenticate(AppleDTO data) {
        return Mono.empty();
//        return appleClient.fetchToken(new AppleTokenRequest(clientId, clientSecret, data.getCode()))
//                .flatMap(this::verifyToken)
//                .flatMap(appleTokenData -> identityRepository.findByHashAndIdentityType(appleTokenData.getId(), IdentityType.APPLE));
    }

    @Override
    public Mono<Identity> create(AppleDTO data) {
        return Mono.empty();
    }
}
