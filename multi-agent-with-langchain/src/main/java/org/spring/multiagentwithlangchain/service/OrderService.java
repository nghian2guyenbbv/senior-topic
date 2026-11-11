package org.spring.multiagentwithlangchain.service;

import org.spring.multiagentwithlangchain.entity.OrderEntity;
import org.spring.multiagentwithlangchain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository repository) {
    this.orderRepository = repository;
  }

  public OrderEntity getOrderStatus(String orderId) {

    return orderRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
  }
}
