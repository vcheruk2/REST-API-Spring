package com.ravi.springmvcrest.services;

import com.ravi.springmvcrest.api.v1.model.VendorDTO;
import com.ravi.springmvcrest.api.v1.model.VendorListDTO;

import java.util.List;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
public interface VendorService {
    VendorListDTO getVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    void deleteVendor(Long id);
    VendorDTO patchVendor(VendorDTO vendorDTO, Long id);
    VendorDTO updateVendor(VendorDTO vendorDTO, Long id);
}
