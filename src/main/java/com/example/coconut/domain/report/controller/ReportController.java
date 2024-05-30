package com.example.coconut.domain.report.controller;


import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public String detail(Model model, @PathVariable("id") Long id){
        Report report = this.reportService.getReport(id);
        model.addAttribute("report", report);
        return "report/detail";
    }

    @GetMapping("/list")
    public String list() {
        return "report/list";
    }

}
