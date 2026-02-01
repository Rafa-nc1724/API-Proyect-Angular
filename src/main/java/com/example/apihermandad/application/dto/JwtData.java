package com.example.apihermandad.application.dto;

public class JwtData {
    private final String token;
    private final long issuedAt;
    private final long expiresAt;

    public JwtData(String token, long issuedAt, long expiresAt) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public long getIssuedAt() { return issuedAt; }
    public long getExpiresAt() { return expiresAt; }
}
