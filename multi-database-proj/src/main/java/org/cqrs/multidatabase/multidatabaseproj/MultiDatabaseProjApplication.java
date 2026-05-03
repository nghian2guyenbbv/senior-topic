package org.cqrs.multidatabase.multidatabaseproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiDatabaseProjApplication {

  public static final String MODEL_PACKAGE = "org.cqrs.multidatabase.multidatabaseproj.model";

  public static void main(String[] args) {
    SpringApplication.run(MultiDatabaseProjApplication.class, args);
  }

}
