package org.cqrs.multidatabase.multidatabaseproj.configurations;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.cqrs.multidatabase.multidatabaseproj.MultiDatabaseProjApplication;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConfigurationProperties("spring.datasource-read")
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "transactionManagerRead",
    entityManagerFactoryRef = "entityManagerFactoryRead", basePackages = {
    "org.cqrs.multidatabase.multidatabaseproj.repository.readRepo" })
public class DataSourceConfigRead extends HikariConfigRead {
  public DataSourceConfigRead(HikariReadProperties hikariReadProperties, JdbcReadProperties jdbcReadProperties) {
    super(hikariReadProperties, jdbcReadProperties);
  }

  @Bean
  public HikariDataSource dataSourceRead() {
    return new HikariDataSource(this);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryRead() {
    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSourceRead());
    factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    factoryBean.setPersistenceUnitName("read");
    factoryBean.setPackagesToScan(MultiDatabaseProjApplication.MODEL_PACKAGE);
    factoryBean.setJpaProperties(jpaReadProperties);

    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManagerRead(@Qualifier("entityManagerFactoryRead") EntityManagerFactory entityManagerFactoryRead) {
    return new JpaTransactionManager(entityManagerFactoryRead);
  }
}
