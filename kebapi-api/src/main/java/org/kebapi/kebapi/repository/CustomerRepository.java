package org.kebapi.kebapi.repository;

import org.kebapi.kebapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
