package com.practice.blog.services;

import java.util.List;

import com.practice.blog.payloads.CategoryDto;

public interface CategoryService {
	
	// CREATE
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// READ SINGLE
	CategoryDto getCategory(Integer categoryId);
	
	// READ ALL
	List<CategoryDto> getCategories();

	// UPDATE
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// DELETE
	void deleteCategory(Integer categoryId);
}
