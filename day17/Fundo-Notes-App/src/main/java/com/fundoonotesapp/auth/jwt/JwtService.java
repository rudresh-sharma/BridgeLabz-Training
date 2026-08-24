package com.fundoonotesapp.auth.jwt;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fundoonotesapp.user.entity.User;

import javax.crypto.SecretKey;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;


    // Generate JWT token
    public String generateToken(User userDetails) {
    	 Instant now = Instant.now();
    	 
        return Jwts.builder()
                .subject(userDetails.getEmail())
                .claim("fullname", userDetails.getName())
                .claim("provider", userDetails.getProvider())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(jwtExpiration)))
                .signWith(getSigningKey())
                .compact();
    }


    // Extract username/email from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public long getJwtExpirationSeconds() {
        return jwtExpiration;
    }
    // Validate token
    public boolean isTokenValid(
            String token,
            UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }


    // Check token expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    // Extract expiration date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    // Generic method to extract any claim
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }


    // Extract all claims
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    // Convert secret key to signing key
    private SecretKey getSigningKey() {

        byte[] keyBytes =
                Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}