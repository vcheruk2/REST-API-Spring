package com.ravi.springmvcrest.api.v1.mapper;

import com.ravi.springmvcrest.api.v1.model.CategoryDTO;
import com.ravi.springmvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/12/2020 */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
