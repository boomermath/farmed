package com.boomermath.farmed.farm.contact.social;

import java.util.Map;

class SocialUrlResolver {
    private static final Map<SocialType, String> socialUrls = Map.of(
            SocialType.FACEBOOK, "https://facebook.com",
            SocialType.INSTAGRAM, "https://instagram.com",
            SocialType.TWITTER, "https://twitter.com");

    public static String urlFromInfo(SocialType socialType, String info) {
        return socialUrls.get(socialType) + info;
    }
}
