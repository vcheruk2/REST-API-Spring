package com.ravi.springmvcrest.api.v1.mapper;

import com.ravi.springmvcrest.api.v1.model.VendorDTO;
import com.ravi.springmvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
