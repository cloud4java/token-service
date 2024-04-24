package com.silvasoft.tokenservice.presentation;

import com.silvasoft.tokenservice.common.JwtUtil;
import com.silvasoft.tokenservice.domain.TokenDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokenControllerIntegrationTest {
    public static final String HOST = "http://localhost:";
    public static final String PATH = "/api/tokens";
    public static final String SECRET = "de90b0b14b014073a76b61340c39da0cde90b0b14b014073a76b61340c39da0c";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testValidToken() {
        final TokenDomain tokenDomain = JwtUtil.generateTokenDomain("NewUserName", SECRET);
        String url = HOST + port + PATH;

        ResponseEntity<String> response = restTemplate.postForEntity(url, tokenDomain, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("true");
    }

    @Test
    public void testInvalidToken() {
        String url = HOST + port + PATH;

        ResponseEntity<String> response = restTemplate.postForEntity(url, JwtUtil.generateTokenDomain("Mary45", SECRET), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("false");
    }
}