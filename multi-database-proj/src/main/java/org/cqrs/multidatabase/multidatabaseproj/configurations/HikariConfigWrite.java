package org.cqrs.multidatabase.multidatabaseproj.configurations;

import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class HikariConfigWrite extends HikariConfig {
  protected final HikariWriteProperties hikariWriteProperties;
  protected final JdbcWriteProperties jdbcWriteProperties;
  protected final Properties jpaWriteProperties;

  public HikariConfigWrite(HikariWriteProperties hikariWriteProperties, JdbcWriteProperties jdbcWriteProperties) {
    this.hikariWriteProperties = hikariWriteProperties;
    this.jdbcWriteProperties = jdbcWriteProperties;
    setPoolName(this.hikariWriteProperties.getPoolName());
    setMinimumIdle(this.hikariWriteProperties.getMinimumIdle());
    setMaximumPoolSize(this.hikariWriteProperties.getMaximumPoolSize());
    setIdleTimeout(this.hikariWriteProperties.getIdleTimeout());
    setJdbcUrl(this.jdbcWriteProperties.getJdbcUrl());
    setUsername(this.jdbcWriteProperties.getUsername());
    setPassword(this.jdbcWriteProperties.getPassword());


    Properties properties = new Properties();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.put("hibernate.show_sql", "true");
    properties.put("hibernate.format_sql", "true");
    this.jpaWriteProperties = properties;

  }
}
