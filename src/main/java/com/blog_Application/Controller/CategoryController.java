package com.blog_Application.Controller;

import com.blog_Application.DAO.CategoryRepository;
import com.blog_Application.DTO.CategoryDto;
import com.blog_Application.Entity.Category;
import com.blog_Application.Response.ResonseWO;
import com.blog_Application.Service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;


    /*@Autowired
    Category category;*/

    @PostMapping("/")
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.addNewCategory(categoryDto);
        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResonseWO> deleteCategoryById(@PathVariable("id") Integer id)
    {
        categoryRepository.deleteById(id);
        ResonseWO resonseWO= new ResonseWO();
        resonseWO.setMessage("Category with id "+id+"deleled successfully");
        return new ResponseEntity<ResonseWO>(resonseWO,HttpStatus.OK);
    }

    @GetMapping("getAll/")
    public ResponseEntity<List<Category>> getAllCategory()
    {
        List<Category> categoryList= categoryService.getAllCategory();
        return  new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id)
    {
        Category category= categoryService.getCategoryById(id);
        return  new ResponseEntity<Category>(category,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id,@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.updateCategoryById(id,categoryDto);
        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }

}