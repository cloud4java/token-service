package com.silvasoft.tokenservice.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokenRequestControllerIntegrationTest {
    public static final String TOKEN_VALID = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
    public static final String TOKEN_INVALID = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
    public static final String HOST = "http://localhost:";
    public static final String PATH = "/api/tokens";

    @LocalServerPort
    private int port = 8087;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testValidInput() {
        String url = HOST + port + PATH;
        String requestBody = "{\"value\": \"" + TOKEN_VALID + "\"}";
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("true");
    }

    @Test
    public void testInvalidInput() {
        String url = HOST + port + PATH;
        String requestBody = "{\"value\": \"" + TOKEN_INVALID + "\"}";

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("false");
    }
}