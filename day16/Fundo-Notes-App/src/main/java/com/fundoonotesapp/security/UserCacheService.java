package com.fundoonotesapp.security;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final String USER_PREFIX = "user:details:";


    public void saveUser(
            CachedUserDetails userDetails,
            long expirationSeconds
    ) {

        try {

            String key = USER_PREFIX
                    + userDetails.getUsername().toLowerCase();

            String value = objectMapper.writeValueAsString(userDetails);

            redisTemplate.opsForValue().set(
                    key,
                    value,
                    Duration.ofSeconds(expirationSeconds)
            );

        } catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Failed to cache user details",
                    e
            );
        }
    }


    public CachedUserDetails getUser(String username) {

        try {

            String key = USER_PREFIX + username.toLowerCase();

            String value =
                    redisTemplate.opsForValue().get(key);

            if (value == null) {
                return null;
            }

            return objectMapper.readValue(
                    value,
                    CachedUserDetails.class
            );

        } catch (JsonProcessingException e) {

            throw new RuntimeException(
                    "Failed to read cached user details",
                    e
            );
        }
    }


    public void removeUser(String username) {

        String key = USER_PREFIX + username.toLowerCase();

        redisTemplate.delete(key);
    }
}