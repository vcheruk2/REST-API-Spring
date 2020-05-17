package com.ravi.springmvcrest.bootstrap;

import com.ravi.springmvcrest.domain.Category;
import com.ravi.springmvcrest.domain.Customer;
import com.ravi.springmvcrest.repositories.CategoryRepository;
import com.ravi.springmvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/12/2020 */
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        // API inspired by https://api.predic8.de/shop/docs#!/categories/getCategories

        loadCustomers();
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
