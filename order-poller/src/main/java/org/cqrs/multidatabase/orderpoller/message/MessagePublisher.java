package org.cqrs.multidatabase.orderpoller.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MessagePublisher {
  private final KafkaTemplate<String, String> kafka;

  @Value("${order.poller.topic.name}")
  private String topicName;

  public void publish(String payload) {
    CompletableFuture<SendResult<String, String>> future = kafka.send(topicName, payload);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        System.out.println("Sent mesage=[ " + payload + " ] with offset[" + result.getRecordMetadata().offset() + "]");
      } else {
        System.out.println("Unable to send message=[ " + payload + " ] due to : " + ex.getMessage());
      }
    });
  }
}
