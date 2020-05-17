package com.ravi.springmvcrest.services;

import com.ravi.springmvcrest.api.v1.mapper.CustomerMapper;
import com.ravi.springmvcrest.api.v1.model.CustomerDTO;
import com.ravi.springmvcrest.controllers.v1.CustomerController;
import com.ravi.springmvcrest.domain.Customer;
import com.ravi.springmvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    private String getUrl(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    void getCustomers() {
        // given
        Customer a = new Customer();
        a.setFirstname("a");

        Customer b = new Customer();
        b.setFirstname("b");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(a,b));

        // when
        List<CustomerDTO> customerDTOList = customerService.getCustomers();

        // then
        assertEquals(2, customerDTOList.size());
    }

    @Test
    void getCustomerByFirstName() {
        // given
        Customer a = new Customer();
        a.setFirstname("a");

        when(customerRepository.findByFirstname(anyString())).thenReturn(a);

        // when
        CustomerDTO customerDTO = customerService.getCustomerByFirstName("a");

        // then
        assertNotNull(customerDTO);
        assertEquals("a", customerDTO.getFirstname());
    }

    @Test
    void getCustomerById() {
        // given
        Customer a = new Customer();
        a.setFirstname("a");
        a.setId(1L);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(a));

        // when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        // then
        assertEquals("a", customerDTO.getFirstname());
    }

    @Test
    void createNewCustomer() {
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("a");

        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setId(1L);

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO mappedCustomerDTO = customerMapper.customerToCustomerDTO(customer);

        // when
        CustomerDTO returnedCustomerDTO = customerService.createNewCustomer(mappedCustomerDTO);

        // then
        assertEquals("a", returnedCustomerDTO.getFirstname());
        assertEquals(getUrl(1L), returnedCustomerDTO.getUrl());
    }

    @Test
    void updateNewCustomer() {
        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("a");

        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setId(1L);

        when(customerRepository.save(any())).thenReturn(customer);

        // when
        CustomerDTO savedCustomerDTO = customerService.updateNewCustomer(customerDTO, 1L);

        // then
        assertEquals("a", savedCustomerDTO.getFirstname());
        assertEquals(getUrl(1L), savedCustomerDTO.getUrl());
    }

    @Test
    void deleteCustomer() {
        // when
        customerService.deleteCustomerById(1L);

        // then
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}