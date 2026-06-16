package org.cqrs.multidatabase.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.cqrs.multidatabase.orderservice.dto.OrderRequestDto;
import org.cqrs.multidatabase.orderservice.entity.Order;
import org.cqrs.multidatabase.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> saveOrder(@RequestBody OrderRequestDto orderRequest) {
    Order createdOrder = orderService.saveOrder(orderRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(createdOrder);
  }
}
