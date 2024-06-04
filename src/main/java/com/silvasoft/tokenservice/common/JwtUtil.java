package com.silvasoft.tokenservice.common;

import com.silvasoft.tokenservice.domain.TokenDomain;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JwtUtil {
    public static final int SEED = 9533;
    private JwtUtil(){}
    public static final String SECRET = System.getenv("SECRET");

    public static void main(String[] args) {

        System.out.println("\nFill the name for token or <ENTER> to exit:");
        final Scanner scanner = new Scanner(System.in);
        String username;
        while ((username = scanner.nextLine()) != null) {
            final String token = JwtUtil.generateToken(username, SECRET);
            System.out.println("SECRET: " + SECRET);
            System.out.println("token: " + token);

            System.out.println("\n\nDecoded: " + JwtUtil.decodeToken(token));

            System.out.println("\nFill the name for token or <ENTER> to exit :");
        }
    }

    public static String generateToken(String username, final String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", username);
        claims.put("role", "admin");
        claims.put("seed", SEED);

        Date expiration = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public static TokenDomain generateTokenDomain(String username, final String secret) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", username);
        claims.put("role", "admin");
        claims.put("seed", SEED);

        Date expiration = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        final String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return new TokenDomain(token, secret);
    }

    private static Map<String, Object> decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}