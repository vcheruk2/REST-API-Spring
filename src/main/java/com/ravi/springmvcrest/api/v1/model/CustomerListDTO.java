package com.ravi.springmvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
@Data
@AllArgsConstructor
public class CustomerListDTO {
    List<CustomerDTO> customers;
}
