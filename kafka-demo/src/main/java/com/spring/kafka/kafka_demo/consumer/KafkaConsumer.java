package com.spring.kafka.kafka_demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
  @KafkaListener(topics = "demo-topic")
  public void consume(String message) {
    System.out.println("Message is: " + message);
  }
}
