package org.spring.streaming.springstreamingresponsebody.controller;

import lombok.RequiredArgsConstructor;
import org.spring.streaming.springstreamingresponsebody.entity.Stock;
import org.spring.streaming.springstreamingresponsebody.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@RestController
@RequestMapping("/stream/stock")
@RequiredArgsConstructor
public class ReactiveController {
  private final StockService stockService;

  @GetMapping("/all-stock")
  public Flux<Stock> streamStock() {
    return stockService.streamStock();
  }

  @GetMapping("/live")
  public Flux<String> getPrice() {
    return Flux.interval(Duration.ofSeconds(1))
        .take(20).map(i -> {
      double price = i * new Random().nextDouble() + 100;
      return "stock price: " + price+ "\n";
    });
  }
}

