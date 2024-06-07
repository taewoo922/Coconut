package com.example.coconut.domain.home.controller;


import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final FreedcsService freedcsService;

    @GetMapping("/")
    public String home(Model model) {
        List<Freedcs> freedcsList = freedcsService.findAllDiscussionsOrderByCreateDateDesc();
        model.addAttribute("freedcsList", freedcsList);
        return "home/main";
    }

}
