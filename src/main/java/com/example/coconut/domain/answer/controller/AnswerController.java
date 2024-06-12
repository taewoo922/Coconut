package com.example.coconut.domain.answer.controller;

import com.example.coconut.domain.answer.AnswerForm;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.answer.service.AnswerService;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.form.ReportReplyForm;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final FreedcsService freedcsService;
    private final DebateService debateService;
    private final AnswerService answerService;
    private final CategoryService categoryService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/free/create/{id}")
    public String f_createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        User user = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("freedcs", freedcs);
            return "discussion/freedcs_detail";
        }

        Answer answer = this.answerService.f_create(freedcs, answerForm.getContent(), user);
        return "redirect:/discussion/free_detail/%s#answer_%s".formatted(answer.getFreedcs().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/debate/create/{id}")
    public String d_createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Debate debate = this.debateService.getDebate(id);
        User user = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("debate", debate);
            return "discussion/d_detail";
        }

        Answer answer = this.answerService.d_create(debate, answerForm.getContent(), user);
        return "redirect:/discussion/d_detail/%s#answer_%s".formatted(answer.getDebate().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "/answer/answerform";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                                    @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/answer/answerform";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/discussion/free_detail/%s#answer_%s", answer.getFreedcs().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/debate/modify/{id}")
    public String d_answerModify(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "/answer/d_answerform";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/debate/modify/{id}")
    public String d_answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                               @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/answer/d_answerform";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/discussion/d_detail/%s#answer_%s", answer.getDebate().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String f_answer_Delete(Principal principal, @PathVariable("id") Long id){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/discussion/free_detail/%s", answer.getFreedcs().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/debate/delete/{id}")
    public String d_answer_Delete(Principal principal, @PathVariable("id") Long id){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/discussion/d_detail/%s", answer.getDebate().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String f_answerVote(Principal principal, @PathVariable("id") Long id) {
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, user);

        return "redirect:/discussion/free_detail/%s#answer_%s".formatted(answer.getFreedcs().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/debate/vote/{id}")
    public String d_answerVote(Principal principal, @PathVariable("id") Long id) {
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, user);

        return "redirect:/discussion/d_detail/%s#answer_%s".formatted(answer.getDebate().getId(), answer.getId());
    }
}