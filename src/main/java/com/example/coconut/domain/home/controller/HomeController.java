package com.example.coconut.domain.home.controller;


import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final FreedcsService freedcsService;
    private  final DebateService debateService;

    @GetMapping("/")
    public String home(Model model) {
        List<Freedcs> freedcsList = freedcsService.findAllDiscussionsOrderByCreateDateDesc();
        model.addAttribute("freedcsList", freedcsList);
        List<Debate> debateList = debateService.findAllDiscussionsOrderByCreateDateDesc();
        model.addAttribute("debateList", debateList);

        // 자유토론과 찬반토론을 병합
        List<Object> combinedList = new ArrayList<>();
        combinedList.addAll(freedcsList);
        combinedList.addAll(debateList);

        // 최신 생성일 기준으로 정렬
        combinedList = combinedList.stream()
                .sorted(Comparator.comparing(d -> {
                    if (d instanceof Freedcs) {
                        return ((Freedcs) d).getCreateDate();
                    } else if (d instanceof Debate) {
                        return ((Debate) d).getCreateDate();
                    }
                    return null;
                }, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        model.addAttribute("combinedList", combinedList);

        List<Freedcs> freedcsBestList = freedcsService.findAllDiscussionsOrderByViewDesc();
        model.addAttribute("freedcsBestList", freedcsBestList);

        List<Debate> debateBestList = debateService.findAllDiscussionsOrderByViewDesc();
        model.addAttribute("debateBestList", debateBestList);

        return "home/main";
    }

}
