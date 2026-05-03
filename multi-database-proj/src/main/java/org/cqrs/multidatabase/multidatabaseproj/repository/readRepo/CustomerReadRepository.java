package org.cqrs.multidatabase.multidatabaseproj.repository.readRepo;

import org.cqrs.multidatabase.multidatabaseproj.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerReadRepository extends CrudRepository<Customer, Long> {
}
