package org.spring.multiagentwithlangchain.tool;

import dev.langchain4j.agent.tool.Tool;
import org.spring.multiagentwithlangchain.entity.OrderEntity;
import org.spring.multiagentwithlangchain.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderTool {
  private OrderService orderService;

  @Tool("Get the current order status and delivery information for an order")
  public OrderEntity getOrderStatus(String orderId){
    return orderService.getOrderStatus(orderId);
  }
}
