package com.ravi.springmvcrest.services;

import com.ravi.springmvcrest.api.v1.model.CategoryDTO;

import java.util.List;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/14/2020 */
public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}