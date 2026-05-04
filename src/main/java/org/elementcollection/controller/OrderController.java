package org.elementcollection.controller;

import lombok.RequiredArgsConstructor;
import org.elementcollection.model.Order;
import org.elementcollection.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  private final OrderService service;

  @PostMapping
  public Order save(@RequestBody Order order) {
    return service.createorder(order);
  }

  @GetMapping
  public List<Order> getAll() {
    return service.getAll();
  }

  @GetMapping("/search")
  public List<Order> search(@RequestParam String product) {
    return service.findByProduct(product);
  }
}
