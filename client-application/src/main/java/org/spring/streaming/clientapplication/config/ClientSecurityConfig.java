package org.spring.streaming.clientapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ClientSecurityConfig {
  @Bean
  public SecurityFilterChain clientFilter(HttpSecurity http) {
    http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).oauth2Login(Customizer.withDefaults())
        .oauth2Client(Customizer.withDefaults());
    return http.build();
  }
}
