package com.example.coconut.domain.category.controller;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.repository.CategoryRepository;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FreedcsRepository freedcsRepository;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category/category_manage";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam("categoryName") String categoryName, Model model) {
        Category category = new Category();
        category.setName(categoryName); // 기본 생성자를 사용하여 인스턴스 생성 후 필드 설정
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/{id}")
    public String showPostsByCategory(@PathVariable("id") Long categoryId, Model model) {
        List<Freedcs> freedcs = freedcsRepository.findByCategoryId(categoryId);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("freedcs", freedcs);
        return "category/category_manage"; // categories.html 템플릿
    }
}
