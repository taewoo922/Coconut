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

import java.util.Collections;
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
        if (keyword.isEmpty()) {
            // 키워드가 비어 있는 경우
            model.addAttribute("keyword", ""); // 빈 문자열을 넘겨줌
            model.addAttribute("debateList", Collections.emptyList()); // 빈 리스트를 넘겨줌
            model.addAttribute("freedcsList", Collections.emptyList()); // 빈 리스트를 넘겨줌
        } else {
            // 키워드가 있는 경우
            List<Debate> debateList = debateService.findByKeyword(keyword);
            List<Freedcs> freedcsList = freedcsService.findByKeyword(keyword);

            model.addAttribute("keyword", keyword); // 키워드를 넘겨줌
            model.addAttribute("debateList", debateList);
            model.addAttribute("freedcsList", freedcsList);
        }

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "discussion/search_results";
    }
}