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
@ConfigurationProperties("spring.datasource-write")
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "transactionWriteManager",
    entityManagerFactoryRef = "entityManagerFactoryWrite", basePackages = {
    "org.cqrs.multidatabase.multidatabaseproj.repository.writeRepo" })
public class DataSourceConfigWrite extends HikariConfigWrite {
  public DataSourceConfigWrite(HikariWriteProperties hikariWriteProperties, JdbcWriteProperties jdbcWriteProperties) {
    super(hikariWriteProperties, jdbcWriteProperties);
  }

  @Bean
  public HikariDataSource dataSourceWrite() {
    return new HikariDataSource(this);
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryWrite() {
    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSourceWrite());
    factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    factoryBean.setPersistenceUnitName("write");
    factoryBean.setPackagesToScan(MultiDatabaseProjApplication.MODEL_PACKAGE);
    factoryBean.setJpaProperties(jpaWriteProperties);
    return factoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionWriteManager(@Qualifier("entityManagerFactoryWrite") EntityManagerFactory writeManagerFactory) {
    return new JpaTransactionManager(writeManagerFactory);
  }

}
