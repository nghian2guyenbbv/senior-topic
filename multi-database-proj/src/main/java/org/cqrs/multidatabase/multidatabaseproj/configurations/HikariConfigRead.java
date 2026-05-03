package org.cqrs.multidatabase.multidatabaseproj.configurations;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class HikariConfigRead extends HikariConfig {
  protected final HikariReadProperties hikariReadProperties;
  protected final Properties jpaReadProperties;
  private final JdbcReadProperties jdbcReadProperties;

  protected HikariConfigRead(HikariReadProperties hikariReadProperties, JdbcReadProperties jdbcReadProperties) {
    this.hikariReadProperties = hikariReadProperties;
    this.jdbcReadProperties = jdbcReadProperties;
    setPoolName(this.hikariReadProperties.getPoolName());
    setMinimumIdle(this.hikariReadProperties.getMinimumIdle());
    setMaximumPoolSize(this.hikariReadProperties.getMaximumPoolSize());
    setIdleTimeout(this.hikariReadProperties.getIdleTimeout());

    setJdbcUrl(this.jdbcReadProperties.getJdbcUrl());
    setUsername(this.jdbcReadProperties.getUsername());
    setPassword(this.jdbcReadProperties.getPassword());

    Properties properties = new Properties();
    properties.put("show-sql", "true");
    this.jpaReadProperties = properties;
  }
}
