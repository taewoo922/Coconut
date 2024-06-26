package com.example.coconut.domain.report.controller;



import com.example.coconut.UnauthorizedAccessException;
import com.example.coconut.domain.ProfanityFilter;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.form.ReportForm;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.reportReply.form.ReportReplyForm;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/base")
    public String bestlist(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Report> paging = this.reportService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "report/base";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Report> paging = this.reportService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "report/list";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, ReportReplyForm reportReplyForm, RedirectAttributes redirectAttributes){
        try {
            Report report = this.reportService.getReport(id);
            model.addAttribute("report", report);
            return "report/detail";
        } catch (UnauthorizedAccessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/report/list";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String reportCreate(ReportForm reportForm){
        return "report/form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String reportCreate(@Valid ReportForm reportForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal){
        if(bindingResult.hasErrors()){
            return "report/form";
        }

        if (ProfanityFilter.containsProfanity(reportForm.getTitle()) || ProfanityFilter.containsProfanity(reportForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return "redirect:/report/create";
        }

        User user = this.userService.getUser(principal.getName());
        this.reportService.create(reportForm.getTitle(), reportForm.getContent(), user, reportForm.getCategory(), reportForm.isSecret());
        return "redirect:/report/list";
    }




    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String reportModify(Model model,ReportForm reportForm, @PathVariable("id") Long id, Principal principal){
        Report report = this.reportService.getReport(id);
        if(!report.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        reportForm.setTitle(report.getTitle());
        reportForm.setContent(report.getContent());
        reportForm.setCategory(report.getCategory());
        reportForm.setSecret(report.isSecret());
        model.addAttribute("reportForm", reportForm);
        return "report/form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reportModify(@Valid ReportForm reportForm, BindingResult bindingResult,
                               Principal principal,RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "report/form";
        }
        Report report = this.reportService.getReport(id);
        if(!report.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        if (ProfanityFilter.containsProfanity(reportForm.getTitle()) || ProfanityFilter.containsProfanity(reportForm.getContent())) {
            // 비속어가 포함되어 있으면 리다이렉트하여 에러 메시지를 전달합니다.
            redirectAttributes.addFlashAttribute("error", "⚠\uFE0F 비속어 사용 금지 ⚠\uFE0F");
            return "redirect:/report/modify/%s".formatted(report.getId());
        }
        this.reportService.modify(report, reportForm.getTitle(), reportForm.getContent(), reportForm.getCategory(), reportForm.isSecret());
        return "redirect:/report/detail/%s".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String reportDelete(Principal principal, @PathVariable("id") Long id){
        Report report = this.reportService.getReport(id);
        if(!report.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.reportService.delete(report);
        return "redirect:/report/list";
    }


}
