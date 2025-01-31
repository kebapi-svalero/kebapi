package org.kebapi.kebapi.service;

import org.kebapi.kebapi.dto.CustomerDto;
import org.kebapi.kebapi.entity.Customer;
import org.kebapi.kebapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public List<CustomerDto> findAll() {
    // Obtienes todos los clientes desde el repositorio
    List<Customer> customers = customerRepository.findAll();

    // Convertir cada cliente a CustomerDto
    return customers.stream()
        .map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getPassword()))
        .collect(Collectors.toList());
  }

  public CustomerDto addCustomer(CustomerDto customerDto) {
    // Convierte CustomerDto a Customer
    Customer customer = new Customer(customerDto.getId(), customerDto.getName(), customerDto.getEmail(), customerDto.getPhone(), customerDto.getPassword());

    // Guarda el cliente en la base de datos
    customer = customerRepository.save(customer);

    // Convierte el cliente guardado a CustomerDto y lo retorna
    return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getPassword());
  }

  public CustomerDto modify(long customerId, CustomerDto customerDto) {
    // Buscar el cliente por ID
    Customer customer = customerRepository.findById(String.valueOf(customerId))
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    // Modificar los datos del cliente
    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setPhone(customerDto.getPhone());
    customer.setPassword(customerDto.getPassword());

    // Guardar el cliente modificado
    customer = customerRepository.save(customer);

    // Retornar el cliente modificado como CustomerDto
    return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getPassword());
  }

  public void deleteCustomer(long customerId) {
    // Eliminar el cliente por su ID
    customerRepository.deleteById(String.valueOf(customerId));
  }
}
