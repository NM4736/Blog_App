package com.blog_Application.ServiceImpl;

import com.blog_Application.DAO.CategoryRepository;
import com.blog_Application.DTO.CategoryDto;
import com.blog_Application.Entity.Category;
import com.blog_Application.Exception.ResourceNotFoundException;
import com.blog_Application.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Category addNewCategory(CategoryDto categoryDto) {

        Category category= new Category();
        category= this.modelMapper.map(categoryDto,Category.class);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategoryById(Integer id) {

    Category   category= categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category with ",""+id," not found"));
    categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategoryById(Integer id,CategoryDto categoryDto) {
        Category   category= categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category with ",""+id," not found"));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        return category;
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category   category= categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category with ",""+id," not found"));
       return category;

    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
