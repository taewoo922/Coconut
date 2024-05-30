package com.example.coconut.domain.discussion_Type.controller;

<<<<<<< HEAD
=======
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.entity.CategoryCode;
import com.example.coconut.domain.category.service.CategoryService;
>>>>>>> a34b5d6 (카테고리)
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

=======
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
>>>>>>> a34b5d6 (카테고리)
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class FreedcsController {
    private final FreedcsService freedcsService;

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/freedcs_list")
    public String list(Model model) {
        List<Freedcs> freedcsList = this.freedcsService.getList();
        model.addAttribute("freedcsList",freedcsList);


        return "discussion/freedcs_list";

    }
    @GetMapping("/free_create")
    public String free_create(FreedcsForm freedcsForm) {

//        return "discussion/free_create_form";
        return "discussion/free_create_form";
    }

    @PostMapping("/free_create")
    public String free_create(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("thumbnail") MultipartFile thumbnail) {

        freedcsService.free_create(title,content,thumbnail);


        return "redirect:/discussion/freedcs_list";
    }

<<<<<<< HEAD
=======
    @ModelAttribute("categories")
    public List<Category> categories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Politics", "정치"));
        categories.add(new Category("Economy", "경제"));
        categories.add(new Category("Sports", "스포츠"));
        return categories;
    }

>>>>>>> a34b5d6 (카테고리)


}
