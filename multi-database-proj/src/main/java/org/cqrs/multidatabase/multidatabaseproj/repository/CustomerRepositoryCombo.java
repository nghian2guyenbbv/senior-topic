package org.cqrs.multidatabase.multidatabaseproj.repository;

import org.cqrs.multidatabase.multidatabaseproj.repository.readRepo.CustomerReadRepository;
import org.cqrs.multidatabase.multidatabaseproj.repository.writeRepo.CustomerWriteRepository;

public interface CustomerRepositoryCombo extends CustomerReadRepository, CustomerWriteRepository {
}
