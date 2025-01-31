package org.kebapi.kebapi.service;


import org.kebapi.kebapi.dto.CustomerInDto;
import org.kebapi.kebapi.dto.CustomerOutDto;
import org.kebapi.kebapi.dto.CustomerRegistrationDto;
import org.kebapi.kebapi.entity.Customer;
import org.kebapi.kebapi.exception.CustomerNotFoundException;
import org.kebapi.kebapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private ModelMapper modelMapper;
  public List<CustomerOutDto> getAll(String name, String email) {
    List<Customer> customerList;

    if (name == null && email == null) {
      customerList = customerRepository.findAll();
    } else if (email == null) {
      customerList = customerRepository.findByName(name);
    } else if (name == null) {
      customerList = customerRepository.findByEmail(email);
    } else {
      customerList = customerRepository.findByNameAndEmail(name, email);
    }

    return modelMapper.map(customerList, new TypeToken<List<CustomerOutDto>>() {
    }.getType());
  }

  public Customer get(long id) throws CustomerNotFoundException {
    return customerRepository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " does not exist"));
  }

  public CustomerOutDto add(CustomerRegistrationDto customerInDto) {
    Customer customer = modelMapper.map(customerInDto, Customer.class);
    Customer newCustomer = customerRepository.save(customer);

    return modelMapper.map(newCustomer, CustomerOutDto.class);
  }

  public List<Customer> getCustomersByFilter(String name, String email,String phone, String password) {
    return customerRepository.findAll((root, query, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();

      if (name != null && !name.isEmpty()) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }
      if (email != null && !email.isEmpty()) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("email"), "%" + email + "%"));
      }
      if (phone != null && !phone.isEmpty()) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
      }
      if (password != null && !password.isEmpty()) {
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("password"), "%" + password + "%"));
      }
      return predicate;
    });
  }

  public CustomerOutDto modify(long customerId, CustomerInDto customerInDto) throws CustomerNotFoundException {
    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(CustomerNotFoundException::new);

    modelMapper.map(customerInDto, customer);
    customerRepository.save(customer);

    return modelMapper.map(customer, CustomerOutDto.class);
  }


  public void remove(long customerId) throws CustomerNotFoundException {
    customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    customerRepository.deleteById(customerId);
  }
}