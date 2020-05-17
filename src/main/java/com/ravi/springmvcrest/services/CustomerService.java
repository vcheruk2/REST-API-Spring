package com.ravi.springmvcrest.services;


import com.ravi.springmvcrest.api.v1.model.CustomerDTO;

import java.util.List;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
public interface CustomerService {
    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomerByFirstName(String name);
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO updateNewCustomer(CustomerDTO customerDTO, Long id);
    CustomerDTO patchCustomer(CustomerDTO customerDTO, Long id);
    void deleteCustomerById(Long id);
}
