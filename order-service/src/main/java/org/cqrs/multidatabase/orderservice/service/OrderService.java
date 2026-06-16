package org.cqrs.multidatabase.orderservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cqrs.multidatabase.orderservice.dto.OrderRequestDto;
import org.cqrs.multidatabase.orderservice.entity.Order;
import org.cqrs.multidatabase.orderservice.entity.OutBox;
import org.cqrs.multidatabase.orderservice.mapper.DtoToOrderMapper;
import org.cqrs.multidatabase.orderservice.mapper.OutBoxMapper;
import org.cqrs.multidatabase.orderservice.repo.OrderRepo;
import org.cqrs.multidatabase.orderservice.repo.OutBoxRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OutBoxRepo outBoxRepo;
  private final OrderRepo orderRepo;
  private final DtoToOrderMapper dtoToOrderMapper;
  private final OutBoxMapper outBoxMapper;

  @Transactional
  public Order saveOrder(OrderRequestDto orderRequest) {
    Order order = dtoToOrderMapper.mapToOrderEntity(orderRequest);
    orderRepo.save(order);
    OutBox outBox = outBoxMapper.mapToOutBox(orderRequest);
    outBoxRepo.save(outBox);
    return order;
  }
}
