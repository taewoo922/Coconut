package com.example.coconut.domain.answer.controller;

import com.example.coconut.domain.answer.AnswerForm;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.answer.service.AnswerService;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")

    public String createAnswer(Model model, @PathVariable("id") Long id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {

    public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm,
                               BindingResult bindingResult, Principal principal) {

        Freedcs freedcs = this.freedcsService.getFreedcs(id);

        User siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("freedcs", freedcs);
            return "discussion/freedcs_detail";
        }
//아래코드에 오류때문에 user 인자 임시 삭제
        Answer answer = this.answerService.create(freedcs, answerForm.getContent(), siteUser);
        return "redirect:/discussion/free_detail/%s#answer_%s".formatted(answer.getFreedcs().getId(), answer.getId());
    }
//@PreAuthorize("isAuthenticated()")
//@GetMapping("/modify/{id}")
//public String reportReplyModify(ReportReplyForm reportReplyForm, @PathVariable("id") Long id, Principal principal){
//    ReportReply reportReply = this.reportReplyService.getReportReply(id);
//    if(!reportReply.getAuthor().getUsername().equals(principal.getName())){
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//    }
//    reportReplyForm.setContent(reportReply.getContent());
//    return "report/reply_form";
//}
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/modify/{id}")
//    public String reportReplyModify(@Valid ReportReplyForm reportReplyForm, BindingResult bindingResult,
//                                    @PathVariable("id") Long id, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            return "report/reply_form";
//        }
//        ReportReply reportReply = this.reportReplyService.getReportReply(id);
//        if (!reportReply.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
//        this.reportReplyService.modify(reportReply, reportReplyForm.getContent());
//        return String.format("redirect:/report/detail/%s#reportReply_%s", reportReply.getReport().getId(), reportReply.getId());
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/delete/{id}")
//    public String reportReplyDelete(Principal principal, @PathVariable("id") Long id){
//        ReportReply reportReply = this.reportReplyService.getReportReply(id);
//        if(!reportReply.getAuthor().getUsername().equals(principal.getName())){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
//        }
//        this.reportReplyService.delete(reportReply);
//        return String.format("redirect:/report/detail/%s", reportReply.getReport().getId());
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(Principal principal, @PathVariable("id") Long id) {
        Answer answer = this.answerService.getAnswer(id);
        User user = this.userService.getUser(principal.getName());
        this.answerService.vote(answer, user);

        return "redirect:/discussion/free_detail/%s#answer_%s".formatted(answer.getFreedcs().getId(), answer.getId());
    }
}
