# Token Processor Service


### Execution help
* Requirements
* * Java 17
* * Gradle 8
* * Spring-boot 3.5

### Gneratin token
* Generate token and signature using the utility class below``` JwtUtil ```
* Fill the name for token or ENTER to exit
```java
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
    public static final String SECRET = "de90b0b14b014073a76b61340c39da0cde90b0b14b014073a76b61340c39da0c";

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
        final String token = generateToken(username, secret);
        return new TokenDomain(token, secret);
    }

    private static Map<String, Object> decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
```

## Use the command velow to call the API
````shell
curl --location 'http://localhost:8080/api/tokens' \
--header 'Content-Type: application/json' \
--data '{
        "signature":"de90b0b14b014073a76b61340c39da0cde90b0b14b014073a76b61340c39da0c",
    "value":"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4iLCJzZWVkIjo5NTMzLCJuYW1lIjoiQW5hIiwic3ViIjoiQW5hIiwiaWF0IjoxNzEzOTU4MDcyLCJleHAiOjE3MjI1OTgwNzJ9.HHg4CVnmA3zPyS8BbnkT3BeI7s7gqegBL9ZbkL6HOCg"
}'
````

### Reference Documentation
Token Processor Service is a Web application which exposes an API that receives a JWT (string) 
    as a parameter and verifies if it is  valid according to the following rules:

* The JWT must be valid
* The JWT must contain only three claims (Name, Role, and Seed)
* The Name claim cannot contain numbers
* The Role claim must contain only one of the three values (Admin, Member, or External)
* The Seed claim must be a prime number
* The maximum size of the Name claim is 256 characters

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

