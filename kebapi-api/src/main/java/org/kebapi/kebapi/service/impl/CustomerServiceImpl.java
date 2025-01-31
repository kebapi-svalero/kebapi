package org.kebapi.kebapi.service.impl;

import java.util.List;
import org.kebapi.kebapi.dto.CustomerDto;
import org.kebapi.kebapi.entity.Customer;
import org.kebapi.kebapi.repository.CustomerRepository;
import org.kebapi.kebapi.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<CustomerDto> findAll() {
    // Convierte la lista de Customer a CustomerDto usando ModelMapper
    return this.modelMapper.map(this.customerRepository.findAll(),
        new TypeToken<List<CustomerDto>>() {}.getType());
  }

  @Override
  public CustomerDto addCustomer(CustomerDto customerDto) {
    // Convertir el CustomerDto a Customer
    Customer customer = this.modelMapper.map(customerDto, Customer.class);

    // Guardar el Customer en la base de datos
    customer = customerRepository.save(customer);

    // Convertir de vuelta a CustomerDto y devolverlo
    return this.modelMapper.map(customer, CustomerDto.class);
  }

  @Override
  public CustomerDto modify(long customerId, CustomerDto customerDto) {
    // Verifica si el cliente existe
    this.customerRepository.findById(String.valueOf(customerId));

    // Reutilizamos el método addCustomer para actualizar el Customer
    return this.addCustomer(customerDto);
  }

  @Override
  public void deleteCustomer(long customerId)  {
    // Verifica si el cliente existe antes de eliminarlo
    this.customerRepository.findById(String.valueOf(customerId));
    this.customerRepository.deleteById(String.valueOf(customerId));
  }
}