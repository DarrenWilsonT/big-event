package com.gientech.bigevent.business.controller;

import com.gientech.bigevent.business.pojo.Category;
import com.gientech.bigevent.business.pojo.Result;
import com.gientech.bigevent.business.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.addCategory(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> listCategory() {
        List<Category> category = categoryService.listCategory();
        return Result.success(category);
    }

    @GetMapping("/detail")
    public Result<Category> detailCategory(Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategory(Integer id) {
        categoryService.delete(id);
        return Result.success();
    }
}
