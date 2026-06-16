package org.cqrs.multidatabase.orderservice.mapper;

import org.cqrs.multidatabase.orderservice.dto.OrderRequestDto;
import org.cqrs.multidatabase.orderservice.entity.OutBox;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OutBoxMapper {
  public OutBox mapToOutBox(OrderRequestDto orderReques) {
    return OutBox.builder().payload(orderReques.getName()).createdAt(new Date()).processed(false).build();
  }
}
