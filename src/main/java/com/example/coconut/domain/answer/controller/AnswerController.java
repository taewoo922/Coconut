package com.example.coconut.domain.answer.controller;

import com.example.coconut.domain.answer.service.AnswerService;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final FreedcsService freedcsService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        this.answerService.create(freedcs, content);
        // TODO: 답변을 저장한다.
        return String.format("redirect:/discussion/free_detail/%s", id);
    }
}