package org.cqrs.multidatabase.multidatabaseproj.service;

import org.cqrs.multidatabase.multidatabaseproj.model.Customer;

import java.util.Optional;

public interface CustomerService {
  Optional<Customer> getCustomer(Long id);

  Customer createCustomer(Customer customer);
}
