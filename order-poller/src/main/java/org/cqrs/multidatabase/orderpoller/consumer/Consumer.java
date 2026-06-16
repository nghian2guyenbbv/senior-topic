package org.cqrs.multidatabase.orderpoller.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
  @Value("order.poller.topic")
  private String topic;

  @KafkaListener(topics = { "order-" }, groupId = "jt-group")
  public void handleUnprocessedTopic(String message) {
    log.info("un-processed message {}", message);
  }
}
