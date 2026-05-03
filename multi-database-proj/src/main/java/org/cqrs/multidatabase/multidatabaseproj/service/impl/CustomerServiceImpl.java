package org.cqrs.multidatabase.multidatabaseproj.service.impl;

import lombok.RequiredArgsConstructor;
import org.cqrs.multidatabase.multidatabaseproj.model.Customer;
import org.cqrs.multidatabase.multidatabaseproj.repository.CustomerRepository;
import org.cqrs.multidatabase.multidatabaseproj.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  @Override
  public Optional<Customer> getCustomer(Long id) {

    return customerRepository.findById(id);
  }

  @Override
  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }
}
