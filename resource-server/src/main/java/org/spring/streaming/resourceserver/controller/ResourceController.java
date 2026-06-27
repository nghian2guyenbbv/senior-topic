package org.spring.streaming.resourceserver.controller;

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
    public Map<String, Object> getResources(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
            "resources", List.of(
                Map.of("id", 1, "name", "Resource A", "type", "document"),
                Map.of("id", 2, "name", "Resource B", "type", "image"),
                Map.of("id", 3, "name", "Resource C", "type", "video")
            ),
            "accessedBy", jwt.getSubject(),
            "scopes", jwt.getClaimAsStringList("scope")
        );
    }

    @GetMapping("/me")
    public Map<String, Object> getMe(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
            "subject", jwt.getSubject(),
            "authorities", jwt.getClaimAsStringList("authorities") != null
                ? jwt.getClaimAsStringList("authorities") : List.of(),
            "scopes", jwt.getClaimAsStringList("scope") != null
                ? jwt.getClaimAsStringList("scope") : List.of(),
            "issuedAt", jwt.getIssuedAt() != null ? jwt.getIssuedAt().toString() : "",
            "expiresAt", jwt.getExpiresAt() != null ? jwt.getExpiresAt().toString() : ""
        );
    }
}
