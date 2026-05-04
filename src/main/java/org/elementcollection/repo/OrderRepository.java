package org.elementcollection.repo;

import org.elementcollection.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("SELECT o FROM Order o JOIN o.items i where i.itemName = :name")
  List<Order> findByItemName(String name);
}
