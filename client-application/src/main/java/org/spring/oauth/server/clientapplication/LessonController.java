package org.spring.oauth.server.clientapplication;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@RestController
public class LessonController {
  private RestClient restClient;
  public LessonController(RestClient restClient) {
    this.restClient = restClient;
  }

  @Value("${resource-server.url:}")
  private String resourceServerUrl;

  @GetMapping("/lessons")
  public String getlesson() {
    return restClient.get()
        .uri(resourceServerUrl)
        .attributes(clientRegistrationId("golf-client"))
        .retrieve()
        .body(String.class);
  }
}
