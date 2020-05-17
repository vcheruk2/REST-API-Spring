package com.ravi.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    private String name;

    @JsonProperty("vendor_url")
    private String url;
}
