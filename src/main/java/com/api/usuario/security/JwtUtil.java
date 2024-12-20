package com.api.usuario.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret";

    private Key secret;

    @PostConstruct
    protected void init(){
        byte[] apiKeySecretBytes = new byte[64];
        new SecureRandom().nextBytes(apiKeySecretBytes);
        secret = Keys.hmacShaKeyFor(apiKeySecretBytes);
    }

    public String generateToken(String email){

        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(exp)
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();
    }

    public boolean validateToken(String token, String email){
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    public String extractEmail(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenExpired(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .build()
                .parseSignedClaims(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
