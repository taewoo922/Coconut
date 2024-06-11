package com.example.coconut.domain.category.controller;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.CategoryForm;
import com.example.coconut.domain.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/category_list";
    }

    @GetMapping("/category/create")
    public String showCreateCategoryForm(CategoryForm categoryForm, Model model) {
        model.addAttribute("categoryForm", categoryForm);
        return "category/category_create_form";
    }

    @PostMapping("/category/create")
    public String createCategory(@Valid CategoryForm categoryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/category_create_form";
        }

        categoryService.createCategory(categoryForm.getName());
        return "/category/category_create_form";
//        return "redirect:/category/category_create_form";
    }

}
