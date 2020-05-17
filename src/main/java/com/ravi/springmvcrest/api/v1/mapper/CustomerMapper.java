package com.ravi.springmvcrest.api.v1.mapper;

import com.ravi.springmvcrest.api.v1.model.CustomerDTO;
import com.ravi.springmvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
