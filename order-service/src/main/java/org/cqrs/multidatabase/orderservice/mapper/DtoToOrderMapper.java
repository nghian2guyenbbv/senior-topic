package org.cqrs.multidatabase.orderservice.mapper;

import org.cqrs.multidatabase.orderservice.dto.OrderRequestDto;
import org.cqrs.multidatabase.orderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DtoToOrderMapper {
  public Order mapToOrderEntity(OrderRequestDto request) {
    return Order.builder()
        .price(BigDecimal.ONE).productType(request.getProductType())
        .name(request.getName())
        .quantity(1)
        .customerId(request.getCustomerId())
        .build();
  }

  ;
}
