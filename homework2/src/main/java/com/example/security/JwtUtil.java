package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    // Use a secure, base64-encoded key (at least 256 bits for HS256)
    private final String jwtSecret = "VGhpcyBpcyBhIHZlcnkgc2VjdXJlIHNlY3JldCBmb3IgdGVzdGluZw";
    private final long jwtExpirationMs = 86400000; // 1 day

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        var builder = Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs));
        if (claims != null) {
            for (Map.Entry<String, Object> entry : claims.entrySet()) {
                builder = builder.claim(entry.getKey(), entry.getValue());
            }
        }
        return builder
                .signWith((SecretKey) getSigningKey())
                .compact();
    }

    public String getSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token).getPayload();
    }
} 