package org.spring.streaming.springstreamingresponsebody.repo;

import org.spring.streaming.springstreamingresponsebody.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long> {
}
