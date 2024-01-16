package com.example.blogapi.services;

import com.example.blogapi.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    CategoryDTO findById(Long id);

    List<CategoryDTO> getAllCategories();

    boolean deleteCategoryById(Long id);
}
