package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
