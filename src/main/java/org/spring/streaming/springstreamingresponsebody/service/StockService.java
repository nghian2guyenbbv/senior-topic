package org.spring.streaming.springstreamingresponsebody.service;

import lombok.RequiredArgsConstructor;
import org.spring.streaming.springstreamingresponsebody.entity.Stock;
import org.spring.streaming.springstreamingresponsebody.repo.StockRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
  private final StockRepo stockRepo;

  public List<Stock> findAllStocks() {
    return stockRepo.findAll();
  }

  public Flux<Stock> streamStock() {
    return Flux.fromIterable(stockRepo.findAll());
  }
}
