package org.cqrs.multidatabase.orderpoller.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cqrs.multidatabase.orderpoller.entity.OutBox;
import org.cqrs.multidatabase.orderpoller.message.MessagePublisher;
import org.cqrs.multidatabase.orderpoller.repository.OutBoxRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.function.Consumer;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class PollerServiceImpl implements PollerService {

  private final OutBoxRepository outBoxRepository;
  private final MessagePublisher messagePublisher;

  @Scheduled(fixedRate = 6000)
  public void pollOrderAndPublicKafka() {
    var notProcessed = outBoxRepository.findByProcessedFalse();
    if (CollectionUtils.isEmpty(notProcessed)) {
      log.error("Not thing to handle");
      return;
    }
    notProcessed.forEach(publicAndUpdateStatus());
  }

  private Consumer<OutBox> publicAndUpdateStatus() {
    return outBox -> {
      try {
        messagePublisher.publish(outBox.getPayload());
        outBox.setProcessed(true);
        outBoxRepository.save(outBox);
      } catch (Exception ex) {
        log.error("Cant publish message: {}", outBox.getPayload());
      }
    };
  }
}
