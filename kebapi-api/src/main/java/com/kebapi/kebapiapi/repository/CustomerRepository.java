package com.kebapi.kebapiapi.repository;

import com.kebapi.kebapiapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>, JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAll();
    List<Customer> findByEmail(String email);
    List<Customer> findByNameAndEmail(String name, String email);
    List<Customer> findByName(String name);

}