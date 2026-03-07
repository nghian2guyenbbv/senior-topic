package org.spring.oauth.server.orderclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client/orders")
public class OrderController {

  private OrderClient orderClient;

  public OrderController(OrderClient client) {
    this.orderClient = client;
  }
  @GetMapping
  public List<Order> getOrder() {
    return orderClient.fetchOrders();
  }
}
