package org.cqrs.multidatabase.orderpoller.service;

public interface PollerService {
  void pollOrderAndPublicKafka();
}
