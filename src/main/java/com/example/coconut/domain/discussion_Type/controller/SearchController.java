package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/discussion")
public class SearchController {

    private final DebateService debateService;
    private final FreedcsService freedcsService;
    private final CategoryService categoryService;

    public SearchController(DebateService debateService, FreedcsService freedcsService, CategoryService categoryService) {
        this.debateService = debateService;
        this.freedcsService = freedcsService;
        this.categoryService = categoryService;
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "kw", defaultValue = "") String keyword, Model model) {

        List<Debate> debateList = debateService.findByKeyword(keyword);
        model.addAttribute("debateList", debateList);


        List<Freedcs> freedcsList = freedcsService.findByKeyword(keyword);
        model.addAttribute("freedcsList", freedcsList);


        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "discussion/search_results";
    }
}