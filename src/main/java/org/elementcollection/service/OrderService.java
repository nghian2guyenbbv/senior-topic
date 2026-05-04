package org.elementcollection.service;

import lombok.RequiredArgsConstructor;
import org.elementcollection.model.Order;
import org.elementcollection.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;

  public Order createorder(Order order) {
    return orderRepository.save(order);
  }

  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  public List<Order> findByProduct(String itemName) {
    return orderRepository.findByItemName(itemName);
  }
}