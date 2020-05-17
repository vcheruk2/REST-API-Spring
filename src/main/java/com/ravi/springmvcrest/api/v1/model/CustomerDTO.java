package com.ravi.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
@Data
public class CustomerDTO {
    String firstname;
    String lastname;

    @JsonProperty("customer_url")
    String url;
}