package com.example.coconut.domain.answer.controller;

import com.example.coconut.domain.ProfanityFilter;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

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
                                 @Valid AnswerForm answerForm, RedirectAttributes redirectAttributes, BindingResult bindingResult, Principal principal) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        User user = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("freedcs", freedcs);
            return "discussion/free_detail";
        }

        if (ProfanityFilter.containsProfanity(answerForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return  "redirect:/discussion/free_detail/%s".formatted(freedcs.getId());
        }

        Answer answer = this.answerService.f_create(freedcs, answerForm.getContent(), user);
        return "redirect:/discussion/free_detail/%s#answer_%s".formatted(answer.getFreedcs().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/debate/create/{id}")
    public String d_createAnswer(Model model, @PathVariable("id") Long id,
                                 @Valid AnswerForm answerForm,RedirectAttributes redirectAttributes, BindingResult bindingResult, Principal principal,
                                 @RequestParam("isSupport") boolean isSupport) {
        Debate debate = this.debateService.getDebate(id);
        User user = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("debate", debate);
            return "discussion/d_detail";
        }

        if (ProfanityFilter.containsProfanity(answerForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return  "redirect:/discussion/d_detail/%s".formatted(debate.getId());
        }

        Answer answer = this.answerService.d_create(debate, answerForm.getContent(), user, isSupport);
        return "redirect:/discussion/d_detail/%s#answer_%s".formatted(answer.getDebate().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal) { //자유토론댓글 수정
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "discussion/freedcs_reply";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                               @PathVariable("id") Long id,RedirectAttributes redirectAttributes, Principal principal) { //자유토론댓글 수정
        if (bindingResult.hasErrors()) {
            return "discussion/freedcs_reply";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        if (ProfanityFilter.containsProfanity(answerForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return  "redirect:/answer/modify/%s".formatted(answer.getId());
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/discussion/free_detail/%s#answer_%s", answer.getFreedcs().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/support/modify/{id}")
    public String supportAnswerModify(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "/discussion/debate_reply";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/support/modify/{id}")
    public String supportAnswerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                                 @PathVariable("id") Long id,RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/discussion/debate_reply";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        if (ProfanityFilter.containsProfanity(answerForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return  "redirect:/answer/support/modify/%s".formatted(answer.getId());
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/discussion/d_detail/%s#answer_%s", answer.getDebate().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/opposition/modify/{id}")
    public String oppositionAnswerModify(AnswerForm answerForm, @PathVariable("id") Long id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "/discussion/debate_reply";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/opposition/modify/{id}")
    public String oppositionAnswerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                                 @PathVariable("id") Long id,RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/discussion/debate_reply";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        if (ProfanityFilter.containsProfanity(answerForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return  "redirect:/answer/opposition/modify/%s".formatted(answer.getId());
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/discussion/d_detail/%s#answer_%s", answer.getDebate().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String f_answer_Delete(Principal principal, @PathVariable("id") Long id) { //자유토론 댓글 삭제 기능
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/discussion/free_detail/%s", answer.getFreedcs().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/support/delete/{id}")
    public String p_answer_Delete(Principal principal, @PathVariable("id") Long id) { //찬성 댓글 삭제 기능
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/discussion/d_detail/%s", answer.getDebate().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/opposition/delete/{id}")
    public String c_answer_Delete(Principal principal, @PathVariable("id") Long id) { //반대 댓글 삭제 기능
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return String.format("redirect:/discussion/d_detail/%s", answer.getDebate().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String f_answerVote(Principal principal, @PathVariable("id") Long id) { //자유토론댓글 추천 기능
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, user);

        return "redirect:/discussion/free_detail/%s#answer_%s".formatted(answer.getFreedcs().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/support/vote/{id}")
    public String p_answerVote(Principal principal, @PathVariable("id") Long id) { //찬성댓글 추천 기능
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, user);

        return "redirect:/discussion/d_detail/%s#answer_%s".formatted(answer.getDebate().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/opposition/vote/{id}")
    public String c_answerVote(Principal principal, @PathVariable("id") Long id) { //반대댓글 추천 기능
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, user);

        return "redirect:/discussion/d_detail/%s#answer_%s".formatted(answer.getDebate().getId(), answer.getId());
    }
}
