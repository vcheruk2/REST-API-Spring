package com.ravi.springmvcrest.controllers.v1;

import com.ravi.springmvcrest.api.v1.model.CategoryDTO;
import com.ravi.springmvcrest.api.v1.model.CategoryListDTO;
import com.ravi.springmvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/14/2020 */
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    private final CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping({"{name}"})
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable("name") String name){
        return categoryService.getCategoryByName(name);
    }
}
