package com.fundoonotesapp.auth.jwt;

import java.io.Serializable;
import java.util.List;

public record CachedUser(
        String email,
        List<String> authorities
) implements Serializable {
}