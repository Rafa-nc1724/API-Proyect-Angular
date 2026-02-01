package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.JwtData;
import com.example.apihermandad.domain.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtData generateToken(Usuario user) {

        long issuedAt = System.currentTimeMillis();
        long expiresAt = issuedAt + expiration;

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(new Date(expiresAt))
                .signWith(getSigningKey())
                .compact();

        return new JwtData(token, issuedAt, expiresAt);
    }
}