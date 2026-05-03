package org.cqrs.multidatabase.multidatabaseproj.repository.writeRepo;

import org.cqrs.multidatabase.multidatabaseproj.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerWriteRepository extends CrudRepository<Customer, Long> {
}
