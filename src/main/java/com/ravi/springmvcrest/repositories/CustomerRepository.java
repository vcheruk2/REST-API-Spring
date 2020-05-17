package com.ravi.springmvcrest.repositories;

import com.ravi.springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
public interface CustomerRepository extends JpaRepository<Customer, Long > {
    Customer findByFirstname(String firstname);
}
