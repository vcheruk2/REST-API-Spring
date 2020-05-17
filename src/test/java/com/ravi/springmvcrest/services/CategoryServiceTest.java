package com.ravi.springmvcrest.services;

import com.ravi.springmvcrest.api.v1.mapper.CategoryMapper;
import com.ravi.springmvcrest.api.v1.model.CategoryDTO;
import com.ravi.springmvcrest.domain.Category;
import com.ravi.springmvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Sasya";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        // given
        Category category = new Category();
        category.setName(NAME);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        when(categoryRepository.findAll()).thenReturn((categoryList));

        // when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        // then
        assertEquals(1, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn((category));

        // when
        CategoryDTO categoryDTOS = categoryService.getCategoryByName(NAME);

        // then
        assertEquals(NAME, categoryDTOS.getName());
        assertEquals(ID, categoryDTOS.getId());
    }
}