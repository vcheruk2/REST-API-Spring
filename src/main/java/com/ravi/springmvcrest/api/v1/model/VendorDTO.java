package com.ravi.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value = "This is the vendor name", required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String url;
}
