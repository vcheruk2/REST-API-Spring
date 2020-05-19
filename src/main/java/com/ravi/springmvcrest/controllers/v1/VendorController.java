package com.ravi.springmvcrest.controllers.v1;

import com.ravi.springmvcrest.api.v1.model.VendorDTO;
import com.ravi.springmvcrest.api.v1.model.VendorListDTO;
import com.ravi.springmvcrest.services.VendorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
@SwaggerDefinition(tags = {@Tag(name = "This is a Vendor Controller", description = "Vendor Controller Description")})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Here are the list of all vendors", notes = "Some notes about the list of vendors")
    public VendorListDTO getVendors(){
        return vendorService.getVendors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.createNewVendor(vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable("id") Long id){
        vendorService.deleteVendor(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable("id") Long id){
        return vendorService.getVendorById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@RequestBody VendorDTO vendorDTO, @PathVariable("id") Long id){
        return vendorService.patchVendor(vendorDTO, id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@RequestBody VendorDTO vendorDTO, @PathVariable("id") Long id){
        return vendorService.updateVendor(vendorDTO, id);
    }
}
