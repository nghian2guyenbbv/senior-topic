package org.spring.streaming.clientapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
public class ClientController {

    private final RestClient restClient;

    public ClientController(@Value("${resource-server.base-url}") String resourceServerUrl) {
        this.restClient = RestClient.builder().baseUrl(resourceServerUrl).build();
    }

    @GetMapping("/")
    public Map<String, Object> home(@AuthenticationPrincipal OidcUser oidcUser) {
        return Map.of(
            "message", "Welcome to Client App",
            "username", oidcUser.getPreferredUsername(),
            "authorities", oidcUser.getAuthorities().stream().map(Object::toString).toList()
        );
    }

    @GetMapping("/resources")
    public String callResources(
            @RegisteredOAuth2AuthorizedClient("auth-server") OAuth2AuthorizedClient authorizedClient) {
        String token = authorizedClient.getAccessToken().getTokenValue();
        return restClient.get()
            .uri("/api/resources")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .retrieve()
            .body(String.class);
    }

    @GetMapping("/me")
    public String callMe(
            @RegisteredOAuth2AuthorizedClient("auth-server") OAuth2AuthorizedClient authorizedClient) {
        String token = authorizedClient.getAccessToken().getTokenValue();
        return restClient.get()
            .uri("/api/me")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .retrieve()
            .body(String.class);
    }
}
