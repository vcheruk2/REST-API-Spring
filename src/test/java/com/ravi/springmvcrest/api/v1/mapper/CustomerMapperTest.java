package com.ravi.springmvcrest.api.v1.mapper;

import com.ravi.springmvcrest.api.v1.model.CustomerDTO;
import com.ravi.springmvcrest.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void customerToCustomerDTO() {
        // given
        Customer customer = new Customer();
        customer.setFirstname("Ravi");

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // then
        assertEquals(customer.getFirstname(), customerDTO.getFirstname());
    }
}