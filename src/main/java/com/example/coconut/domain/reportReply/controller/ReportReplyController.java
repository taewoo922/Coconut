package com.example.coconut.domain.reportReply.controller;


import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.form.ReportReplyForm;
import com.example.coconut.domain.reportReply.service.ReportReplyService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        this.reportReplyService.create(report, reportReplyForm.getContent(), user);
        return "redirect:/report/detail/%s".formatted(id);
    }

}
