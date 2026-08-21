package com.fundoonotesapp.auth.jwt;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenCacheService {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String TOKEN_PREFIX = "jwt:token:";

    public void saveToken(
            String username,
            String token,
            long expirationMillis
    ) {

        String key = TOKEN_PREFIX + username;

        redisTemplate.opsForValue().set(
                key,
                token,
                Duration.ofMillis(expirationMillis)
        );
    }

    public String getToken(String username) {

        String key = TOKEN_PREFIX + username;

        return redisTemplate.opsForValue().get(key);
    }

    public void removeToken(String username) {

        String key = TOKEN_PREFIX + username;

        redisTemplate.delete(key);
    }

    public boolean isTokenCached(
            String username,
            String token
    ) {

        String cachedToken = getToken(username);
        System.out.println("Cached token: " + cachedToken);
        System.out.println("Request token: " + token);

        return token.equals(cachedToken);
    }
}