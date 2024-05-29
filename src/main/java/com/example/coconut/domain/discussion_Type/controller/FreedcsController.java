package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class FreedcsController {
    private final FreedcsService freedcsService;



    @GetMapping("/freedcs_list")
    public String list(Model model) {
        List<Freedcs> freedcsList = this.freedcsService.getList();
        model.addAttribute("freedcsList",freedcsList);

        return "discussion/freedcs_list";

    }
    @GetMapping("/free_create")
    public String free_create() {
        return "discussion/free_create_form";
    }

    @PostMapping("/free_create")
    public String free_create(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("thumbnail") MultipartFile thumbnail) {
        freedcsService.free_create(title,content,thumbnail);

        return "redirect:/discussion/freedcs_list";
    }



}
