package com.sapo.jwt.controller;

import com.sapo.jwt.model.entity.Category;
import com.sapo.jwt.model.request.CategoryRequest;
import com.sapo.jwt.sevice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {

    private CategoryService  categoryService;

    @Autowired
    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> findAll(@RequestParam int page) {
        return categoryService.findAll(page);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable long id) {
        return categoryService.findOne(id);
    }

    @PostMapping()
    public Category create(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.create(categoryRequest);
    }

    @PutMapping()
    public Category update(@RequestBody CategoryRequest categoryRequest, @RequestParam Long id, @RequestParam Boolean status) {
        return categoryService.update(categoryRequest, id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteByUpdateStatus(id);
    }
}
