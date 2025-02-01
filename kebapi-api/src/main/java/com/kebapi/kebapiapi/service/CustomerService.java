package com.kebapi.kebapiapi.service;


import com.kebapi.kebapiapi.domain.Customer;
import com.kebapi.kebapiapi.domain.dto.CustomerInDto;
import com.kebapi.kebapiapi.domain.dto.CustomerOutDto;
import com.kebapi.kebapiapi.domain.dto.CustomerRegistrationDto;
import com.kebapi.kebapiapi.exception.CustomerNotFoundException;
import com.kebapi.kebapiapi.repository.CustomerRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
