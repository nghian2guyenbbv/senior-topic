package org.spring.streaming.oauthorazationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  @Order(1)
  public SecurityFilterChain oauthFilterChain(HttpSecurity http) {
    http.oauth2AuthorizationServer(oauth -> {
      http.securityMatcher(oauth.getEndpointsMatcher());
      oauth.oidc(Customizer.withDefaults());
    });
    http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).exceptionHandling(ex -> {
      MediaTypeRequestMatcher htmlMatcher = new MediaTypeRequestMatcher(MediaType.TEXT_HTML);
      htmlMatcher.setIgnoredMediaTypes(Set.of(MediaType.ALL));
    }).oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults()));
    return http.build();
  }

  @Bean
  @Order(2)
  public UserDetailsService userDetailService(PasswordEncoder passwordEncoder) {
    UserDetails userDetails = User.builder().username("user").password(passwordEncoder.encode("password")).roles("USER")
        .build();
    return new InMemoryUserDetailsManager(userDetails);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
