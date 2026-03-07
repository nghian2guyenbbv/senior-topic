package org.spring.oauth.server.orderclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderClient {
  private RestClient restClient;

  public OrderClient(@LoadBalanced RestClient.Builder loadBalance) {
    this.restClient = loadBalance.baseUrl("http://order-service").build();
  }

  public List<Order> fetchOrders() {
    return restClient.get().uri("/api/service/orders").retrieve().body(new ParameterizedTypeReference<>() {
    });
  }
}
