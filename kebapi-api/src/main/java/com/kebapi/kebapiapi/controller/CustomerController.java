package com.kebapi.kebapiapi.controller;


import com.kebapi.kebapiapi.domain.Customer;
import com.kebapi.kebapiapi.domain.dto.CustomerInDto;
import com.kebapi.kebapiapi.domain.dto.CustomerOutDto;
import com.kebapi.kebapiapi.domain.dto.CustomerRegistrationDto;
import com.kebapi.kebapiapi.exception.CustomerNotFoundException;
import com.kebapi.kebapiapi.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<List<CustomerOutDto>> getAll(@RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "email", required = false) String email) {

        List<CustomerOutDto> customers = customerService.getAll(name, email);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long customerId) throws CustomerNotFoundException {
        Customer customer = customerService.get(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerOutDto> addCustomer(@RequestBody @Valid CustomerRegistrationDto customer) {
        CustomerOutDto newCustomer = customerService.add(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerOutDto> modifyCustomer(@PathVariable @Valid long customerId, @RequestBody CustomerInDto customer) throws CustomerNotFoundException {
        CustomerOutDto modifiedCustomer = customerService.modify(customerId, customer);
        return new ResponseEntity<>(modifiedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> removeCustomer(@PathVariable long customerId) throws CustomerNotFoundException{
        customerService.remove(customerId);
        return ResponseEntity.noContent().build();
    }
}

