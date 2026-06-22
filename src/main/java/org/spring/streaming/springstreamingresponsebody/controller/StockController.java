package org.spring.streaming.springstreamingresponsebody.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.spring.streaming.springstreamingresponsebody.entity.Stock;
import org.spring.streaming.springstreamingresponsebody.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/stock")
public class StockController {
  private final StockService stockService;

  @GetMapping(value = "/all-stock")
  public List<Stock> getStock() {
    return stockService.findAllStocks();
  }

  @GetMapping(value = "/streaming-stock")
  public StreamingResponseBody getStockStream(HttpServletResponse response) {
    response.setContentType("text/event-stream");
    var allStock = stockService.findAllStocks();
    return (OutputStream outStream) -> {
      allStock.forEach(st -> {
        try {
          var stJson = new ObjectMapper().writeValueAsString(st) + "/n";
          outStream.write(stJson.getBytes());
          outStream.flush();
        } catch (IOException e) {
          throw new RuntimeException(e.getMessage());
        }
      });
    };

  }

  @GetMapping("/live")
  public StreamingResponseBody streamStockPrices(HttpServletResponse response) {
    response.setContentType("text/event-stream"); // so browser/clients treat it as a stream

    return (OutputStream outputStream) -> {
      try {
        for (int i = 0; i < 200; i++) { // simulate 20 updates
          double price = 100 + new Random().nextDouble() * 10; // random stock price
          String update = "Stock Price: " + price + "\n";

          outputStream.write(update.getBytes());
          outputStream.flush();

          Thread.sleep(1000); // every 1 second new update
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    };
  }
}
