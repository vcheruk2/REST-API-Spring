package com.ravi.springmvcrest.repositories;

import com.ravi.springmvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
