package org.spring.oauth.server.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/service/orders")
public class OrderServiceController {

  @GetMapping
  public List<Order> getOrder() {
    return Arrays.asList(new Order("1", "1", 1), new Order("2", "2", 2));
  }
}
