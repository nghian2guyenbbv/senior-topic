package org.spring.streaming.resourceserver;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResourceController {

  @GetMapping("/resources")
  public Map<String, Object> getResource(@AuthenticationPrincipal Jwt jwt) {
    return Map.of("resource", List.of(Map.of("id", 1 , "name", "Resource A")),
        "accessBy", jwt.getSubject(),
        "scope", jwt.getClaimAsStringList("scope"));
  }
}
