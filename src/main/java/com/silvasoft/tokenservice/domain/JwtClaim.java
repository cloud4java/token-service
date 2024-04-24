package com.silvasoft.tokenservice.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtClaim {
    private String name;
    private int seed;
    private String role;

    public JwtClaim(String name, int seed, String role) {
        this.name = name;
        this.seed = seed;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(final int seed) {
        this.seed = seed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public static JwtClaim of(String token, final String tokenSecret) {
        Claims body = Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();
        return new JwtClaim(body.get("name", String.class),
                body.get("seed", Integer.class),
                body.get("role", String.class));

    }
}
