package com.spring.kafka.kafka_demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private String TOPIC = "demo-topic";

  public KafkaProducer(KafkaTemplate<String, String> template) {
    this.kafkaTemplate = template;
  }

  public void send(String message) {
    kafkaTemplate.send(TOPIC, message);
  }
}
