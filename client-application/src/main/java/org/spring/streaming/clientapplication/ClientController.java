package org.spring.streaming.clientapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@RestController
public class ClientController {
  private final RestClient restClient;
  public ClientController(@Value("${resource-server.base-url}") String resourceServerUrl) {
    this.restClient = RestClient.builder().baseUrl(resourceServerUrl).build();
  }

  @GetMapping("/resources")
  public String callResource(@RegisteredOAuth2AuthorizedClient("auth-server")OAuth2AuthorizedClient oauthClient) {
    String token = oauthClient.getAccessToken().getTokenValue();
  return restClient.get()
      .uri("/api/resources")
      .header(HttpHeaders.AUTHORIZATION, "Bearer"+token)
      .retrieve()
      .body(String.class);
  }
}
