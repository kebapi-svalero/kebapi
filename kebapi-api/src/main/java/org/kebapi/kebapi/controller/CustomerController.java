package org.kebapi.kebapi.controller;

import org.kebapi.kebapi.dto.CustomerDto;
import org.kebapi.kebapi.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping
  public ResponseEntity<List<CustomerDto>> getAll() {
    List<CustomerDto> customers = customerService.findAll();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
    CustomerDto newCustomer = customerService.addCustomer(customerDto);
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<CustomerDto> modifyCustomer(@PathVariable long customerId,
      @Valid @RequestBody CustomerDto customerDto) {
    CustomerDto modifiedCustomer = customerService.modify(customerId, customerDto);
    return new ResponseEntity<>(modifiedCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> removeCustomer(@PathVariable long customerId) {
    customerService.deleteCustomer(customerId);
    return ResponseEntity.noContent().build();
  }
}
