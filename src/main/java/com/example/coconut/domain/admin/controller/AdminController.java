package com.example.coconut.domain.admin.controller;

import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@Getter
@Setter
public class AdminController {

    private final UserService userService;
    private final ReportService reportService;
    private final FreedcsService freedcsService;
    private final DebateService debateService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userlist")
    public String userlist(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "kw", defaultValue = "") String kw
    ) {

        Page<User> paging = userService.getList(page, kw);;

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "manager/admin";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/questionlist")
    public String questionlist(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "kw", defaultValue = "") String kw
    ) {

        Page<Report> paging = reportService.getList(page, kw);;

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "manager/reportlist";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/freedcslist")
    public String freedcslist(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "kw", defaultValue = "") String kw
    ) {
        Page<Freedcs> paging = freedcsService.getList(page, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "manager/freedcslist";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/debatelist")
    public String debatelist(Model model,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "kw", defaultValue = "") String kw
    ) {
        Page<Debate> paging = debateService.getList(page, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "manager/debatelist";
    }

}
