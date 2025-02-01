package com.kebapi.kebapiapi.repository;

import com.kebapi.kebapiapi.domain.Customer;
import com.kebapi.kebapiapi.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>, JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    List<Item> findAll();
    List<Item> findByName(String name);
    List<Item> findByPrice(float price);
    List<Item> findByDescription(String description);
    List<Item> findByNameAndDescription(String name, String description);
}