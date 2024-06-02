package com.example.coconut.domain.reportReply.controller;


import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.form.ReportForm;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.form.ReportReplyForm;
import com.example.coconut.domain.reportReply.service.ReportReplyService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/reportReply")
public class ReportReplyController {

    private final ReportService reportService;
    private final ReportReplyService reportReplyService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createReply(Model model, @PathVariable("id") Long id, @Valid ReportReplyForm reportReplyForm,
                              BindingResult bindingResult, Principal principal){
        Report report = this.reportService.getReport(id);
        User user = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("report", report);
            return "report/detail";
        }
        ReportReply reportReply = this.reportReplyService.create(report, reportReplyForm.getContent(), user);
        return "redirect:/report/detail/%s#reportReply_%s".formatted(reportReply.getReport().getId(), reportReply.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String reportReplyModify(ReportReplyForm reportReplyForm, @PathVariable("id") Long id, Principal principal){
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        if(!reportReply.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        reportReplyForm.setContent(reportReply.getContent());
        return "report/reply_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reportReplyModify(@Valid ReportReplyForm reportReplyForm, BindingResult bindingResult,
                               @PathVariable("id") Long id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "report/reply_form";
        }
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        if (!reportReply.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.reportReplyService.modify(reportReply, reportReplyForm.getContent());
        return String.format("redirect:/report/detail/%s#reportReply_%s", reportReply.getReport().getId(), reportReply.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String reportReplyDelete(Principal principal, @PathVariable("id") Long id){
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        if(!reportReply.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.reportReplyService.delete(reportReply);
        return String.format("redirect:/report/detail/%s", reportReply.getReport().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String reportReplyVote(Principal principal, @PathVariable("id") Long id) {
        ReportReply reportReply = this.reportReplyService.getReportReply(id);
        User user = this.userService.getUser(principal.getName());
        this.reportReplyService.vote(reportReply, user);
        return "redirect:/report/detail/%s#reportReply_%s".formatted(reportReply.getReport().getId(), reportReply.getId());
    }

}
