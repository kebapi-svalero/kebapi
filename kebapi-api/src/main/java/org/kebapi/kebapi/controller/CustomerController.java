package org.kebapi.kebapi.controller;

import java.util.List;
import org.kebapi.kebapi.dto.CustomerInDto;
import org.kebapi.kebapi.dto.CustomerOutDto;
import org.kebapi.kebapi.dto.CustomerRegistrationDto;
import org.kebapi.kebapi.entity.Customer;
import org.kebapi.kebapi.exception.CustomerNotFoundException;
import org.kebapi.kebapi.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

  @GetMapping("/search")
  public ResponseEntity<List<Customer>> getCustomerByFilter(
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "email", required = false) String email,
     @RequestParam(value = "phone", required = false) String phone,
     @RequestParam(value = "password", required = false) String password)
     {

    List<Customer> customers = customerService.getCustomersByFilter(name, email, phone, password);
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerOutDto> addCustomer(@RequestBody CustomerRegistrationDto customer) {
    CustomerOutDto newCustomer = customerService.add(customer);
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<CustomerOutDto> modifyCustomer(@PathVariable long customerId, @RequestBody CustomerInDto customer) throws CustomerNotFoundException {
    CustomerOutDto modifiedCustomer = customerService.modify(customerId, customer);
    return new ResponseEntity<>(modifiedCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> removeCustomer(@PathVariable long customerId) throws CustomerNotFoundException{
    customerService.remove(customerId);
    return ResponseEntity.noContent().build();
  }

}