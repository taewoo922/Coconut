package com.example.coconut.domain.answer.controller;

import com.example.coconut.domain.answer.AnswerForm;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.answer.service.AnswerService;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.question.service.QuestionService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final FreedcsService freedcsService;
    private final AnswerService answerService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        this.answerService.create(freedcs, content);




        // TODO: 답변을 저장한다.
        return String.format("redirect:/discussion/free_detail/%d", id);
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/create/{id}")
//    public String createAnswer(Model model,
//                               @PathVariable("id") Integer id,
//                               @Valid AnswerForm answerForm,
//                               BindingResult bindingResult,
//                               Principal principal) {
//        //답변 부모 질문객체를 받아온다.
//        Freedcs freedcs = this.freedcsService.getFreedcs(id);
//        User user = this.userService.getUser(principal.getName());
//
//        if ( bindingResult.hasErrors() ) {
//            model.addAttribute("freedcs", freedcs) ;
//            return "freedcs_detail";
//        }
//
//        Answer answer = this.answerService.create(freedcs, answerForm.getContent(), user);
//
//        return "redirect:/discussion/free_detail/%d#answer_%d".formatted(answer.getFreedcs().getId(), id);
//    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());


        this.answerService.vote(answer, user);

        return "redirect:/discussion/free_detail/%d#answer_%d".formatted(answer.getFreedcs().getId(), id);
    }
}