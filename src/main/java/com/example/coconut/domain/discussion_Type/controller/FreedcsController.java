package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class FreedcsController {
    private final FreedcsService freedcsService;
    private final UserService userService;

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
    public String free_create(@RequestParam("title") String title, @RequestParam("content") String content,
                              @RequestParam("thumbnail") MultipartFile thumbnail) {

        freedcsService.free_create(title,content,thumbnail);


        return "redirect:/discussion/freedcs_list";
    }

    @GetMapping(value = "/free_detail/{id}")
    public String free_detail(Model model, @PathVariable("id") Integer id) {

        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        model.addAttribute("freedcs", freedcs);

        return "discussion/freedcs_detail";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String free_Vote(Principal principal, @PathVariable("id") Integer id) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        User user = this.userService.getUser(principal.getName());

        this.freedcsService.vote(freedcs, user);

        return String.format("redirect:/discussion/freedcs_detail/%s".formatted(id));
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Politics", "정치"));
        categories.add(new Category("Economy", "경제"));
        categories.add(new Category("Sports", "스포츠"));
        return categories;
    }



}
