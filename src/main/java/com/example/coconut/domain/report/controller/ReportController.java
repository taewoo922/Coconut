package com.example.coconut.domain.report.controller;



import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.form.ReportForm;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.reportReply.form.ReportReplyForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {



    private final ReportService reportService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Report> reportList = this.reportService.getList();

        model.addAttribute("reportList", reportList);
        return "report/list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, ReportReplyForm reportReplyForm){
        Report report = this.reportService.getReport(id);
        model.addAttribute("report", report);
        return "report/detail";
    }

    @GetMapping("/create")
    public String reportCreate(ReportForm reportForm){
        return "report/form";
    }

    @PostMapping("/create")
    public String reportCreate(@Valid ReportForm reportForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "report/form";
        }
        this.reportService.create(reportForm.getTitle(), reportForm.getContent());
        return "redirect:/report/list";

    }

}
