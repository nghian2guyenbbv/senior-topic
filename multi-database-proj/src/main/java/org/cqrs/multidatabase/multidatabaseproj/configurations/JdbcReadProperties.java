package org.cqrs.multidatabase.multidatabaseproj.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties("spring.jdbc-read")
public class JdbcReadProperties {
  private String jdbcUrl;
  private String username;
  private String password;
}
