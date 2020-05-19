package com.ravi.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/15/2020 */
@Data
public class CustomerDTO {

    @ApiModelProperty(value = "This is FirstName", required = true)
    String firstname;

    @ApiModelProperty(value = "This is LastName", required = true)
    String lastname;

    @JsonProperty("customer_url")
    String url;
}