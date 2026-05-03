package org.cqrs.multidatabase.multidatabaseproj.controller;

import org.cqrs.multidatabase.multidatabaseproj.exception.ResourceNotFoundException;
import org.cqrs.multidatabase.multidatabaseproj.model.Customer;
import org.cqrs.multidatabase.multidatabaseproj.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/customer/{id}")
  public Customer getCustomer(@PathVariable("id") Long id) {

    return customerService.getCustomer(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Customer"));
  }

  @PostMapping("/customer")
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerService.createCustomer(customer);
  }

}
