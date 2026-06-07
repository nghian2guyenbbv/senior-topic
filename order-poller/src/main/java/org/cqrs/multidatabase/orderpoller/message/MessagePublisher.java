package org.cqrs.multidatabase.orderpoller.message;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagePublisher {
  private final KafkaTemplate<String, String> kafka;

  public void publishMessage(String topic, String message) {

    // Implement your message publishing logic here, e.g., using KafkaTemplate for Kafka
    // kafkaTemplate.send(topic, message);
    kafka.send(topic, message);
    System.out.println("Publishing message to topic: " + topic + ", message: " + message);
  }
}
