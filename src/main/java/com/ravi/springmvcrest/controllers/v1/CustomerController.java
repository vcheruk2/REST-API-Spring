package com.ravi.springmvcrest.controllers.v1;

import com.ravi.springmvcrest.api.v1.model.CustomerDTO;
import com.ravi.springmvcrest.api.v1.model.CustomerListDTO;
import com.ravi.springmvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    private final CustomerService customerService;
    public static final String BASE_URL = "/api/v1/customers";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomers(){
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getCustomers()), HttpStatus.OK);
    }

    @GetMapping("/firstname/{name}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable("name") String firstName){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> putNewCustomer(@RequestBody CustomerDTO customerDTO,
                                                      @PathVariable("id") Long id){
        return new ResponseEntity<CustomerDTO>(customerService.updateNewCustomer(customerDTO, id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDTO,
                                                     @PathVariable("id") Long id){
        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(customerDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerListDTO> deleteCustomerById(@PathVariable("id") Long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getCustomers()), HttpStatus.OK);
    }
}
