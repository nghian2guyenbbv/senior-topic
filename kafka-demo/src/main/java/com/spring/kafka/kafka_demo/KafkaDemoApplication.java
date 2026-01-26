package com.spring.kafka.kafka_demo;

import com.spring.kafka.kafka_demo.producer.KafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KafkaDemoApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(KafkaDemoApplication.class, args);
    var producer = context.getBean(KafkaProducer.class);
    producer.send("message from kafka producer");

  }

}
