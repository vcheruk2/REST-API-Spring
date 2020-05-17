package com.ravi.springmvcrest.bootstrap;

import com.ravi.springmvcrest.domain.Category;
import com.ravi.springmvcrest.domain.Customer;
import com.ravi.springmvcrest.domain.Vendor;
import com.ravi.springmvcrest.repositories.CategoryRepository;
import com.ravi.springmvcrest.repositories.CustomerRepository;
import com.ravi.springmvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/12/2020 */
@Component

public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        // API inspired by https://api.predic8.de/shop/docs#!/categories/getCategories

        loadCustomers();

        loadVendors();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");

        Vendor vendor3 = new Vendor();
        vendor3.setName("Vendor3");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);

        System.out.println("Loaded Vendors " + vendorRepository.count());
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count() );
    }

    private void loadCustomers() {
        Customer ravi = new Customer();
        ravi.setFirstname("Ravi");
        ravi.setLastname("Cherukuri");

        Customer sundar = new Customer();
        sundar.setFirstname("Sundar");
        sundar.setLastname("Pich");

        customerRepository.save(ravi);
        customerRepository.save(sundar);

        //System.out.println("Customer data size = " + customerRepository.findAll().size());
        System.out.println("Customer data size = " + customerRepository.count());
    }
}
