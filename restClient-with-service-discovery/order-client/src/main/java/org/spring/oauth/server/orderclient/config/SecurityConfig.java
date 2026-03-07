package org.spring.oauth.server.orderclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  @Order(2)
  public SecurityFilterChain filterLogin(HttpSecurity http) {
    return http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).formLogin(Customizer.withDefaults())
        .build();
  }

  @Bean
  @Order(1)
  public SecurityFilterChain filterClientDetail(HttpSecurity http) {
    OAuth2AuthorizationServerConfigurer configurer = new OAuth2AuthorizationServerConfigurer();

    http.securityMatcher(configurer.getEndpointsMatcher())
        .with(configurer, Customizer.withDefaults())
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .exceptionHandling(ex -> ex.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));

    return http.build();
  }

  @Bean
  public RegisteredClientRepository client() {
    RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .clientSettings(ClientSettings.builder()
            .requireProofKey(false)
            .build())
        .clientId("nghia-client")
        .clientSecret("{noop}nghia-secret")
        .redirectUri("http://localhost:8082/api/client/orders")
        .scope("read")
        .build();
    return new InMemoryRegisteredClientRepository(client);
  }

  @Bean
  public UserDetailsService userService() {
    UserDetails user = User.withUsername("nghia").password("{noop}nghia").roles("USER").build();
    return new InMemoryUserDetailsManager(user);
  }
}
