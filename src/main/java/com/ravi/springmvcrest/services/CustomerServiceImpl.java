package com.ravi.springmvcrest.services;

import com.ravi.springmvcrest.api.v1.mapper.CustomerMapper;
import com.ravi.springmvcrest.api.v1.model.CustomerDTO;
import com.ravi.springmvcrest.controllers.v1.CustomerController;
import com.ravi.springmvcrest.domain.Customer;
import com.ravi.springmvcrest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setUrl(getUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByFirstName(String name) {
        return customerMapper.customerToCustomerDTO(customerRepository.findByFirstname(name));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            return customerMapper.customerToCustomerDTO(customer.get());
        else
            throw new ResourceNotFoundException("Unable to find the customer");
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return saveOrUpdateCustomer(customer);
    }

    private CustomerDTO saveOrUpdateCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTO.setUrl(getUrl(savedCustomer.getId()));
        return customerDTO;
    }

    @Override
    public CustomerDTO updateNewCustomer(CustomerDTO customerDTO, Long id) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveOrUpdateCustomer(customer);
    }

    @Override
    public CustomerDTO patchCustomer(CustomerDTO customerDTO, Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent()) {
            throw new ResourceNotFoundException("Unable to find customer with id " + id);
        }

        Customer customer = customerOptional.get();
        Customer receivedCustomer = customerMapper.customerDTOToCustomer(customerDTO);

        if(receivedCustomer.getFirstname() != null)
            customer.setFirstname(receivedCustomer.getFirstname());

        if (receivedCustomer.getLastname() != null)
            customer.setLastname(receivedCustomer.getLastname());

        return saveOrUpdateCustomer(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private String getUrl(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }
}