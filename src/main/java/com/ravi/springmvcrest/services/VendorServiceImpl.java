package com.ravi.springmvcrest.services;

import com.ravi.springmvcrest.api.v1.mapper.VendorMapper;
import com.ravi.springmvcrest.api.v1.model.VendorDTO;
import com.ravi.springmvcrest.api.v1.model.VendorListDTO;
import com.ravi.springmvcrest.controllers.v1.VendorController;
import com.ravi.springmvcrest.domain.Vendor;
import com.ravi.springmvcrest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorListDTO getVendors() {
        List<VendorDTO> vendorDTOList = vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOList);
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private VendorDTO saveVendor(Vendor vendor){
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDTO.setUrl(getVendorUrl(savedVendor.getId()));
        return vendorDTO;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveVendor(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDTO patchVendor(VendorDTO vendorDTO, Long id) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);

        if (vendorOptional.isPresent()){
            Vendor vendor = vendorOptional.get();

            if (vendorDTO.getName() != null)
                vendor.setName(vendorDTO.getName());

            return saveVendor(vendor);
        }
        else{
            new ResourceNotFoundException("Vendor with Id " + id + " not found.");
            return null;
        }
    }

    @Override
    public VendorDTO updateVendor(VendorDTO vendorDTO, Long id) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);
        return saveVendor(vendor);
    }

    private String getVendorUrl(Long id){
        return VendorController.BASE_URL + "/" + id;
    }
}
