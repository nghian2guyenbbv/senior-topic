package org.cqrs.multidatabase.orderservice.repo;

import org.cqrs.multidatabase.orderservice.entity.OutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutBoxRepo extends JpaRepository<OutBox, Long> {
}
