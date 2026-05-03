package org.cqrs.multidatabase.multidatabaseproj.repository;

import org.cqrs.multidatabase.multidatabaseproj.model.Customer;
import org.cqrs.multidatabase.multidatabaseproj.repository.readRepo.CustomerReadRepository;
import org.cqrs.multidatabase.multidatabaseproj.repository.writeRepo.CustomerWriteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerRepository implements CustomerRepositoryCombo {
  private final CustomerReadRepository customerReadRepo;
  private final CustomerWriteRepository customerWriteRepo;

  public CustomerRepository(CustomerReadRepository customerReadRepo, CustomerWriteRepository customerWriteRepo) {
    this.customerReadRepo = customerReadRepo;
    this.customerWriteRepo = customerWriteRepo;
  }

  @Override
  public <S extends Customer> S save(S custom) {
    return customerWriteRepo.save(custom);
  }

  @Override
  public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
    return customerWriteRepo.saveAll(entities);
  }

  @Override
  public Optional<Customer> findById(Long aLong) {
    return customerReadRepo.findById(aLong);
  }

  @Override
  public boolean existsById(Long aLong) {
    return customerReadRepo.existsById(aLong);
  }

  @Override
  public Iterable<Customer> findAll() {
    return customerReadRepo.findAll();
  }

  @Override
  public Iterable<Customer> findAllById(Iterable<Long> longs) {
    return customerReadRepo.findAllById(longs);
  }

  @Override
  public long count() {
    return customerReadRepo.count();
  }

  @Override
  public void deleteById(Long aLong) {
    customerWriteRepo.deleteById(aLong);
  }

  @Override
  public void delete(Customer entity) {
    customerWriteRepo.delete(entity);
  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {
    customerWriteRepo.deleteAllById(longs);
  }

  @Override
  public void deleteAll(Iterable<? extends Customer> entities) {
    customerWriteRepo.deleteAll(entities);
  }

  @Override
  public void deleteAll() {
    customerWriteRepo.deleteAll();
  }
}
