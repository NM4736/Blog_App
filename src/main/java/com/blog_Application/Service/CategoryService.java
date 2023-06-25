package com.blog_Application.Service;

import com.blog_Application.DTO.CategoryDto;
import com.blog_Application.Entity.Category;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CategoryService {

    Category addNewCategory(CategoryDto categoryDto);
    void deleteCategoryById(Integer id);
    Category updateCategoryById(Integer id, CategoryDto categoryDto);
    Category getCategoryById(Integer id);

    List<Category> getAllCategory();


}
