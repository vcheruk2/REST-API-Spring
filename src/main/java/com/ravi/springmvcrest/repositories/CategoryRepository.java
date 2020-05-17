package com.ravi.springmvcrest.repositories;

import com.ravi.springmvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/12/2020 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
