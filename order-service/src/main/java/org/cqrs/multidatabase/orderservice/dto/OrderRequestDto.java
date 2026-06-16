package org.cqrs.multidatabase.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
  private String name;
  private String customerId;
  private String productType;
  private int quantity;
  private BigDecimal price;
}
